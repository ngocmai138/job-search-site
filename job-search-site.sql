drop database if exists `spring_workcv`;
create database if not exists `spring_workcv`
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
use `spring_workcv`;

drop table if exists `category`;
create table `category`(
						`id` int(11) auto_increment,
                        `name` varchar(255),
                        `number_choose` int(11),
                        `isActive` tinyint(1) default 1,
                        primary key(`id`));
                        
drop table if exists `user`;
create table `user`(
					`id` int(11) auto_increment,
                    `address` varchar(255),
                    `description` text,
                    `email` varchar(255) unique,
                    `user_name` varchar(255) unique,
                    `image` varchar(255),
                    `password` varchar(255),
                    `phone_number` varchar(255),
                    `status` int(11) default 0,
					`isActive` tinyint(1) default 1,
                    primary key (`id`),
                    INDEX idx_username (`user_name`));
                    
drop table if exists `cv`;
create table `cv`(
					`id` int(11) auto_increment,
                    `file_name` varchar(255),
                    `user_id` int(11) unique,
                    `isActive` tinyint(1) default 1,
                    primary key(`id`),
                    constraint `fk_cv_user` foreign key(`user_id`) references `user`(id));

drop table if exists `role`;
create table `role`(
					`id` int(11) auto_increment,
                    `role_name` varchar(255) unique,
                    primary key(`id`));

drop table if exists `authorities`;
create table `authorities`(
					`id` int(11) auto_increment,
					`user_id` int(11) not null,
                    `role_id` int(11) not null,
                    primary key(`id`),
                    constraint `fk_authorities_user` foreign key(`user_id`) references `user`(id),
                    constraint `fk_authotities_role` foreign key(`role_id`) references `role`(id));
                    
drop table if exists `company`;
create table `company`(
						`id` int(11) auto_increment,
                        `address` varchar(255) null,
                        `description` text null,
                        `email` varchar(255) null,
                        `logo` varchar(255) null,
                        `name_company` varchar(255) null,
						`phone_number` varchar(255) null,
                        `status` int(11) default 1,
                        `user_id` int(11) unique,
                        `isActive` tinyint(1) default 1,
                        `number_of_recruitments` int(11) default 0,
                        primary key(`id`),
                        constraint `fk_company_user` foreign key(`user_id`)references `user`(id));
                        
drop table if exists `recruitment`;
create table `recruitment`(
							`id` int(11) auto_increment,
							`address` varchar(255),
							`created_at` varchar(255),
                            `description` varchar(355),
                            `experience` varchar(255),
                            `quantity` int(11),
                            `rank` varchar(255),
                            `salary` varchar(255),
                            `status` int(11),
                            `title` varchar(255),
                            `type` varchar(255),
                            `view` int(11),
                            `category_id` int(11),
                            `company_id` int(11),
                            `deadline` varchar(255),
                            `isActive` tinyint(1) default 1,
                            primary key(`id`),
                            constraint `fk_recruitment_category` foreign key (`category_id`) references `category`(id),
                            constraint `fk_recruitment_company` foreign key(`company_id`) references `company`(id));
                            
drop table if exists `save_job`;
create table `save_job`(
						`id` int(11) auto_increment,
                        `recruitment_id` int(11),
                        `user_id` int(11),
                        primary key (`id`),
                        constraint `fk_sj_recruitment` foreign key(`recruitment_id`) references `recruitment` (id),
                        constraint `fk_sj_user` foreign key(`user_id`) references `user`(id));
                        
drop table if exists `follow_company`;
create table `follow_company`(
								`id` int(11) auto_increment,
                                `company_id` int(11),
                                `user_id` int(11),
                                primary key(`id`),
                                constraint `fk_fc_company` foreign key(`company_id`) references `company`(id),
                                constraint `fk_fc_user` foreign key(`user_id`) references `user`(id));
                                
drop table if exists `applypost`;
create table `applypost`(
							`id` int(11) auto_increment,
                            `created_at` varchar(255),
                            `recruitment_id` int(11),
                            `user_id` int(11),
                            `name_cv` varchar(255),
                            `status` int(11),
                            `text` varchar(255),
							primary key (`id`),
                            constraint `fk_ap_recruitment` foreign key (`recruitment_id`) references `recruitment`(id),
                            constraint `fk_ap_user` foreign key(`user_id`) references `user`(id));

drop table if exists `verification_token`;
create table `verification_token`(
									`id` int(11) auto_increment primary key,
                                    `token` varchar(255) not null,
                                    `user_id` int(11) not null,
                                    `expiry_date` timestamp not null,
                                    constraint `fk_vt_user` foreign key(`user_id`) references `user`(id));

-- Trigger khi thêm dữ liệu vào bảng authorities, tự động tạo cv hoặc company tương ứng
DELIMITER //

CREATE TRIGGER after_authorities_insert
AFTER INSERT ON `authorities`
FOR EACH ROW
BEGIN
    -- Tạo Company nếu role_id là 1
    IF NEW.role_id = 1 THEN
        INSERT INTO `company` (`address`, `description`, `email`, `user_id`, `isActive`)
        VALUES ('Default Address', 'Default Description', (SELECT email FROM `user` WHERE id = NEW.user_id), NEW.user_id, 1);
    END IF;

    -- Tạo CV nếu role_id là 2
    IF NEW.role_id = 2 THEN
        INSERT INTO `cv` (`file_name`, `user_id`, `isActive`)
        VALUES (CONCAT('user', NEW.user_id, '_cv.pdf'), NEW.user_id, 1);
    END IF;
END //

DELIMITER ;


-- Tạo trigger để khi isActive của User thay đổi thì Cv hoặc company cũng thay đổi tương ứng
DELIMITER //

CREATE TRIGGER update_related_isActive AFTER UPDATE ON `user`
FOR EACH ROW
BEGIN
    -- Cập nhật isActive của company nếu có liên quan
    UPDATE `company` SET isActive = NEW.isActive WHERE user_id = NEW.id;
    -- Cập nhật isActive của cv nếu có liên quan
    UPDATE `cv` SET isActive = NEW.isActive WHERE user_id = NEW.id;
END //

DELIMITER ;

-- Trigger cập nhật number_of_recruitments của company khi thuộc tính isActive của recruitment thay đổi
DELIMITER //

CREATE TRIGGER after_recruitment_update
AFTER UPDATE ON recruitment
FOR EACH ROW
BEGIN
    IF NEW.isActive <> OLD.isActive THEN
        UPDATE company c
        SET c.number_of_recruitments = (SELECT COUNT(*) FROM recruitment r WHERE r.company_id = c.id AND r.isActive = 1)
        WHERE c.id = NEW.company_id;
    END IF;
END //

DELIMITER ;

DELIMITER //

CREATE TRIGGER after_recruitment_insert
AFTER INSERT ON recruitment
FOR EACH ROW
BEGIN
    IF NEW.isActive = 1 THEN
        UPDATE company c
        SET c.number_of_recruitments = (SELECT COUNT(*) FROM recruitment r WHERE r.company_id = c.id AND r.isActive = 1)
        WHERE c.id = NEW.company_id;
    END IF;
END //

DELIMITER ;

DELIMITER //

CREATE TRIGGER after_recruitment_delete
AFTER DELETE ON recruitment
FOR EACH ROW
BEGIN
    UPDATE company c
    SET c.number_of_recruitments = (SELECT COUNT(*) FROM recruitment r WHERE r.company_id = c.id AND r.isActive = 1)
    WHERE c.id = OLD.company_id;
END //

DELIMITER ;

-- Chèn dữ liệu mẫu vào bảng category
INSERT INTO `category` (`name`, `number_choose`) VALUES 
('IT', 10), 
('Marketing', 15), 
('Sales', 20), 
('HR', 25), 
('Finance', 30);

-- Chèn dữ liệu mẫu vào bảng role
INSERT INTO `role` (`role_name`) VALUES 
('ROLE_recruiter'), 
('ROLE_candidate');

-- Chèn dữ liệu mẫu vào bảng user
INSERT INTO `user` (`address`, `description`, `email`, `user_name`, `image`, `password`, `phone_number`)
VALUES ('123 Main St', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'phuthuynho1381@gmail.com', 'user1', 'person_1.jpg', '{noop}123', '1234567890');

INSERT INTO `user` (`address`, `description`, `email`, `user_name`, `image`, `password`, `phone_number`)
VALUES ('456 Elm St', 'Pellentesque egestas faucibus nunc, id tincidunt mauris suscipit eget. Ut ac sapien in risus facilisis malesuada. Fusce id gravida diam. Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eu ornare felis. Fusce a tellus a tellus ultrices egestas placerat in neque. Praesent non condimentum ante. Etiam nec porttitor turpis.', 'user2@example.com', 'user2', 'person_2.jpg', '{noop}123', '0987654321');

INSERT INTO `user` (`address`, `description`, `email`, `user_name`, `image`, `password`, `phone_number`)
VALUES ('789 Oak St', 'Duis vitae blandit massa, at gravida sapien. Morbi semper mattis urna nec auctor. Sed quis sodales dui, in ornare dolor. Vivamus quis sodales turpis. Duis vitae elit urna. Nulla varius nunc a enim ultrices efficitur. Nulla congue est a vestibulum tincidunt.', 'user3@example.com', 'user3', 'person_3.jpg', '{noop}123', '1231231234');

INSERT INTO `user` (`address`, `description`, `email`, `user_name`, `image`, `password`, `phone_number`)
VALUES ('321 Pine St', 'Etiam at libero massa. Aliquam aliquet aliquet venenatis. Donec rutrum risus nec quam accumsan, at condimentum lectus dignissim. Suspendisse nec diam mi. Curabitur hendrerit vehicula lobortis. Proin ullamcorper diam non libero sollicitudin, a pellentesque dolor cursus. Nam ac facilisis velit.', 'user4@example.com', 'user4', 'person_4.jpg', '{noop}123', '3213214321');

INSERT INTO `user` (`address`, `description`, `email`, `user_name`, `image`, `password`, `phone_number`,`status`)
VALUES ('654 Maple St', 'Praesent consectetur erat eros, venenatis consequat augue volutpat quis. Proin porttitor hendrerit dignissim. Phasellus lorem elit, malesuada a lectus in, faucibus ullamcorper ante. Nunc ac est eget ipsum faucibus euismod vulputate interdum ipsum. Morbi maximus luctus dui nec gravida.', 'user5@example.com', 'user5', 'person_5.jpg', '{noop}123', '4564564567','1');



-- Chèn dữ liệu mẫu vào bảng authorities
INSERT INTO `authorities` (`user_id`, `role_id`) VALUES 
('1', '1'), 
('2', '1'), 
('3', '1'), 
('4', '1'), 
('5', '2');

-- Cập nhật dữ liệu trong bảng company
UPDATE `company` SET
    `address` = '111 Business Rd',
    `description` = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut porttitor eros ut purus tempus auctor et sit amet velit. Proin vulputate vel justo ac elementum. Quisque consectetur urna ut mollis eleifend. Praesent lobortis neque a enim venenatis, sed ultricies orci placerat. Praesent pellentesque sit amet nisl id facilisis. Phasellus in tellus at lectus lacinia ultricies eu quis lorem. Sed id ante eget ante pretium varius non vel justo.',
    `email` = 'contact@company1.com',
    `logo` = 'company-1.jpg',
    `name_company` = 'Company One',
    `phone_number` = '1111111111',
    `status` = 1
WHERE `user_id` = 1;

UPDATE `company` SET
    `address` = '222 Commerce St',
    `description` = 'Nam aliquam tincidunt enim, at vulputate lacus laoreet scelerisque. Nunc finibus ornare malesuada. Integer malesuada metus enim, in volutpat neque mattis eget. Vestibulum sodales tellus consequat ipsum dapibus, sit amet elementum nunc facilisis. Morbi accumsan, erat et viverra vehicula, ex diam faucibus est, eget sollicitudin elit augue sit amet risus. Duis a eros purus. Nullam et lorem vitae enim blandit convallis in a arcu.',
    `email` = 'contact@company2.com',
    `logo` = 'company-2.jpg',
    `name_company` = 'Company Two',
    `phone_number` = '2222222222',
    `status` = 1
WHERE `user_id` = 2;

UPDATE `company` SET
    `address` = '333 Market St',
    `description` = 'Ut a sodales mi. Mauris vitae dignissim augue, eu eleifend diam. Curabitur odio diam, tempus eu elit non, cursus convallis nunc. Pellentesque tortor enim, vestibulum nec neque eget, feugiat laoreet felis. Fusce eget sapien nec nibh facilisis pretium. Fusce sollicitudin enim non elit venenatis ultrices. Aliquam eu suscipit sem.',
    `email` = 'contact@company3.com',
    `logo` = 'company-3.jpg',
    `name_company` = 'Company Three',
    `phone_number` = '3333333333',
    `status` = 1
WHERE `user_id` = 3;

UPDATE `company` SET
    `address` = '444 Enterprise Ave',
    `description` = 'Cras commodo nibh quis nisl bibendum molestie. Ut vel lacus iaculis, hendrerit metus vitae, accumsan enim. Pellentesque at magna a massa iaculis maximus. Integer et consequat enim. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi aliquam tortor vitae tempus elementum.',
    `email` = 'contact@company4.com',
    `logo` = 'company-4.jpg',
    `name_company` = 'Company Four',
    `phone_number` = '4444444444',
    `status` = 1
WHERE `user_id` = 4;

-- Chèn dữ liệu mẫu vào bảng recruitment
INSERT INTO `recruitment` (`address`, `created_at`, `description`, `experience`, `quantity`, `rank`, `salary`, `status`, `title`, `type`, `view`, `category_id`, `company_id`, `deadline`) VALUES 
('101 Hiring Rd', '2024-06-01', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam et egestas justo. Etiam tristique nisl in molestie egestas. Morbi venenatis molestie elementum. Vivamus a pretium velit. Phasellus vulputate diam nunc, at bibendum dolor luctus vel. Maecenas nec sodales mauris, id luctus dolor. Sed lobortis orci quis accumsan posuere. ', '2 years', 5, 'Senior', '1000-2000', 1, 'Job Title 1', 'Full-time', 100, 1, 1, '2024-07-01'), 
('202 Employment St', '2024-06-02', 'Etiam sed fringilla nunc, eget dignissim erat. Duis at ligula ultrices, lacinia sapien et, vehicula massa. Nam blandit risus quis ornare lacinia. Vivamus semper ante a velit congue ultrices vitae ac ante. Nullam maximus at sem sit amet eleifend. Quisque libero nunc, hendrerit at dignissim vulputate, vehicula eu ipsum.', '3 years', 3, 'Junior', '1500-2500', 1, 'Job Title 2', 'Part-time', 200, 2, 2, '2024-07-02'), 
('303 Career Ln', '2024-06-03', 'Duis eget odio cursus, condimentum odio ut, cursus nunc. Donec sit amet eros eget ipsum ultricies bibendum vel eu ante. Duis facilisis tincidunt odio, eu luctus odio tempor nec. Cras faucibus suscipit eros, non condimentum urna tincidunt non. In hac habitasse platea dictumst. ', '4 years', 4, 'Mid-level', '2000-3000', 1, 'Job Title 3', 'Contract', 300, 3, 3, '2024-07-03'), 
('404 Job Ave', '2024-06-04', 'Nullam id commodo neque. Pellentesque rutrum sapien non auctor porta. Suspendisse vitae eros sit amet neque malesuada sollicitudin. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', '5 years', 2, 'Executive', '2500-3500', 1, 'Job Title 4', 'Temporary', 400, 4, 4, '2024-07-04'),
('505 Hiring Rd', '2024-07-01', 'Proin eget odio cursus, condimentum odio ut, cursus nunc. Donec sit amet eros eget ipsum ultricies bibendum vel eu ante. Duis facilisis tincidunt odio, eu luctus odio tempor nec. Cras faucibus suscipit eros, non condimentum urna tincidunt non. In hac habitasse platea dictumst.', '2 years', 3, 'Manager', '3000-4000', 1, 'Job Title 5', 'Full-time', 500, 1, 2, '2024-08-01'),
('606 Employment St', '2024-07-02', 'Nullam id commodo neque. Pellentesque rutrum sapien non auctor porta. Suspendisse vitae eros sit amet neque malesuada sollicitudin. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', '3 years', 2, 'Senior', '3500-4500', 1, 'Job Title 6', 'Part-time', 600, 2, 2, '2024-08-02'),
('707 Career Ln', '2024-07-03', 'Duis eget odio cursus, condimentum odio ut, cursus nunc. Donec sit amet eros eget ipsum ultricies bibendum vel eu ante. Duis facilisis tincidunt odio, eu luctus odio tempor nec. Cras faucibus suscipit eros, non condimentum urna tincidunt non. In hac habitasse platea dictumst.', '4 years', 5, 'Junior', '2000-3000', 1, 'Job Title 7', 'Contract', 700, 3, 2, '2024-08-03'),
('808 Job Ave', '2024-07-04', 'Proin eget odio cursus, condimentum odio ut, cursus nunc. Donec sit amet eros eget ipsum ultricies bibendum vel eu ante. Duis facilisis tincidunt odio, eu luctus odio tempor nec. Cras faucibus suscipit eros, non condimentum urna tincidunt non. In hac habitasse platea dictumst.', '5 years', 1, 'Executive', '4000-5000', 1, 'Job Title 8', 'Temporary', 800, 4, 2, '2024-08-04'),
('909 Hiring Rd', '2024-07-05', 'Nullam id commodo neque. Pellentesque rutrum sapien non auctor porta. Suspendisse vitae eros sit amet neque malesuada sollicitudin. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.', '6 years', 4, 'Mid-level', '2500-3500', 1, 'Job Title 9', 'Full-time', 900, 5, 2, '2024-08-05');


-- Chèn dữ liệu mẫu vào bảng save_job
INSERT INTO `save_job` (`recruitment_id`, `user_id`) VALUES 
(1, 1), 
(2, 2), 
(3, 3), 
(4, 4), 
(4, 5);

-- Chèn dữ liệu mẫu vào bảng follow_company
INSERT INTO `follow_company` (`company_id`, `user_id`) VALUES 
(1, 1), 
(2, 2), 
(3, 3), 
(4, 4), 
(2, 5);

-- Chèn dữ liệu mẫu vào bảng applypost
INSERT INTO `applypost` (`created_at`, `recruitment_id`, `user_id`, `status`, `text`) VALUES 
('2024-06-06', 1, 4, 1, 'Application text 1'), 
('2024-06-07', 2, 3, 1, 'Application text 2'), 
('2024-06-08', 3, 2, 1, 'Application text 3'), 
('2024-06-09', 4, 1, 1, 'Application text 4'), 
('2024-06-10', 3, 5, 1, 'Application text 5');
