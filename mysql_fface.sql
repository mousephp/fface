-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: mysql_fface:3306
-- Generation Time: Jul 17, 2023 at 02:35 AM
-- Server version: 8.0.32
-- PHP Version: 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mysql_fface`
--

-- --------------------------------------------------------

--
-- Table structure for table `chat`
--

CREATE TABLE `chat` (
  `id` bigint NOT NULL,
  `user1_id` bigint DEFAULT NULL,
  `user2_id` bigint DEFAULT NULL,
  `last_content` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `chat`
--

INSERT INTO `chat` (`id`, `user1_id`, `user2_id`, `last_content`, `date_created`) VALUES
(1, 3, 2, 'test', '2023-07-05 11:35:35'),
(2, 1, 2, 'sned-test', '2023-07-05 11:36:13'),
(3, 1, 3, 'sned-noline', '2023-07-05 11:36:27');

-- --------------------------------------------------------

--
-- Table structure for table `chat1`
--

CREATE TABLE `chat1` (
  `id` bigint NOT NULL,
  `sender_id` bigint DEFAULT NULL,
  `receiver_id` bigint DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `chat1`
--

INSERT INTO `chat1` (`id`, `sender_id`, `receiver_id`, `content`, `status`, `time`) VALUES
(1, 1, 3, 'send-message', b'0', '2023-07-05 11:23:47'),
(2, 3, 1, 'reply-send-message', b'0', '2023-07-05 11:24:18'),
(3, 2, 3, 'test-send-', b'1', '2023-07-05 11:25:32'),
(4, 2, 1, 'test-test', b'1', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `comment_post_user`
--

CREATE TABLE `comment_post_user` (
  `id` bigint NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `user_info_post_id` bigint DEFAULT NULL,
  `post_user_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `comment_post_user`
--

INSERT INTO `comment_post_user` (`id`, `content`, `date_created`, `user_info_post_id`, `post_user_id`) VALUES
(1, 'test-test', '2023-07-05 10:27:34', 1, 1),
(2, 'test-xxx', '2023-07-05 10:28:19', 3, 1),
(5, 'hello world', '2023-07-05 10:39:14', 1, 1),
(6, 'hajha - hello world', '2023-07-05 10:39:54', 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `friendship`
--

CREATE TABLE `friendship` (
  `id` bigint NOT NULL,
  `from_user_id` bigint DEFAULT NULL,
  `to_user_id` bigint DEFAULT NULL,
  `status` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `groups_`
--

CREATE TABLE `groups_` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `background` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `group_status_id` bigint DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_delete` bit(1) DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `groups_`
--

INSERT INTO `groups_` (`id`, `name`, `avatar`, `background`, `user_id`, `group_status_id`, `created_at`, `updated_at`, `is_delete`) VALUES
(1, 'tets', '1651423516341minh.png', NULL, 1, 1, NULL, NULL, b'0'),
(2, 'tets-x1', '1651423516341minh.png', 'string', 1, 1, NULL, NULL, b'0'),
(3, 'test-abc', '1651423516341minh.png', 'test-string', 2, 2, '2023-07-12 09:39:11', '2023-07-13 11:07:52', b'0'),
(4, NULL, '1651423516341minh.png', NULL, NULL, NULL, '2023-07-13 13:44:58', '2023-07-13 13:44:58', b'1'),
(5, NULL, '1651423516341minh.png', NULL, 3, NULL, '2023-07-13 13:51:07', '2023-07-13 13:51:07', b'1'),
(9, 'online', '1651423516341minh.png', 'online', 3, 2, '2023-07-13 14:03:18', '2023-07-13 14:03:18', b'0'),
(10, 'online1', '1651423516341minh.png', 'online1', 3, 2, '2023-07-13 16:31:03', '2023-07-13 16:31:03', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `group_members`
--

CREATE TABLE `group_members` (
  `id` bigint NOT NULL,
  `group_id` bigint DEFAULT NULL,
  `user_info_id` bigint DEFAULT NULL,
  `status` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `group_members`
--

INSERT INTO `group_members` (`id`, `group_id`, `user_info_id`, `status`) VALUES
(1, 1, 1, 1),
(2, 1, 2, 1),
(3, 3, 1, 0),
(4, 4, 3, 0),
(5, 5, 3, 1),
(6, 9, 3, 1),
(7, 10, 3, 0),
(10, 3, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `group_status`
--

CREATE TABLE `group_status` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `group_status`
--

INSERT INTO `group_status` (`id`, `name`) VALUES
(1, 'test'),
(2, 'abc');

-- --------------------------------------------------------

--
-- Table structure for table `image_post_group`
--

CREATE TABLE `image_post_group` (
  `id` bigint NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `post_group_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `image_post_group`
--

INSERT INTO `image_post_group` (`id`, `image`, `post_group_id`) VALUES
(1, 'Anh-hai-huoc-cung-lac-da.jpg', 1),
(2, 'Anh-hai-huoc-cung-lac-da.jpg', 2),
(3, 'Anh-hai-huoc-cung-lac-da.jpg', 3);

-- --------------------------------------------------------

--
-- Table structure for table `image_post_user`
--

CREATE TABLE `image_post_user` (
  `id` bigint NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `post_user_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `image_post_user`
--

INSERT INTO `image_post_user` (`id`, `image`, `post_user_id`) VALUES
(1, 'Anh-hai-huoc-su-tu-ngoi-xe.jpg', 1),
(2, 'Anh-hai-huoc-su-tu-ngoi-xe.jpg', 3),
(3, 'Anh-hai-huoc-bo-duoi.jpg', 4),
(4, 'photo-2-16692745885412057746227 (1).jpg', 4),
(5, 'cuu-thi-sinh-olympia.jpg1688526362762', 5),
(6, 'Anh-hai-huoc-dua-xe-dap.jpg1688526605816', 6),
(7, '1688526724422img_girl.jpg', 7);

-- --------------------------------------------------------

--
-- Table structure for table `like_comment`
--

CREATE TABLE `like_comment` (
  `id` bigint NOT NULL,
  `from_user_id` bigint DEFAULT NULL,
  `comment_post_user_id` bigint DEFAULT NULL,
  `status` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `like_comment`
--

INSERT INTO `like_comment` (`id`, `from_user_id`, `comment_post_user_id`, `status`) VALUES
(1, 3, 6, 0),
(2, 1, 5, 0),
(3, 1, 2, 0);

-- --------------------------------------------------------

--
-- Table structure for table `like_post_user`
--

CREATE TABLE `like_post_user` (
  `id` bigint NOT NULL,
  `status` bit(1) NOT NULL,
  `post_user_id` bigint DEFAULT NULL,
  `user_info_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `like_post_user`
--

INSERT INTO `like_post_user` (`id`, `status`, `post_user_id`, `user_info_id`) VALUES
(1, b'1', 1, 3),
(2, b'1', 3, 1),
(3, b'1', 5, 2),
(4, b'1', 7, 2),
(6, b'1', 6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `list_friend`
--

CREATE TABLE `list_friend` (
  `id` bigint NOT NULL,
  `user_info_id` bigint DEFAULT NULL,
  `friends_of_userinfo_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `list_friend`
--

INSERT INTO `list_friend` (`id`, `user_info_id`, `friends_of_userinfo_id`) VALUES
(1, 1, 2),
(2, 1, 3),
(3, 3, 2),
(4, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `id` bigint NOT NULL,
  `from_user_id` bigint DEFAULT NULL,
  `to_user_id` bigint DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `status` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`id`, `from_user_id`, `to_user_id`, `date_created`, `content`, `status`) VALUES
(1, 2, 1, '2023-07-05 09:20:12', 'null đã xin phép đăng 1 bài viết trong nhóm ', 4),
(2, 3, 1, '2023-07-05 11:07:19', 'Thích bài viết của bạn!', 2),
(3, 1, 1, '2023-07-05 11:08:00', 'Thích bài viết của bạn!', 2),
(4, 2, 2, '2023-07-05 11:08:13', 'Thích bài viết của bạn!', 2),
(5, 2, 3, '2023-07-05 11:08:17', 'Thích bài viết của bạn!', 2),
(6, 1, 3, '2023-07-05 11:08:40', 'Thích bài viết của bạn!', 2),
(7, 1, 3, '2023-07-05 11:11:19', 'Thích bài viết của bạn!', 2),
(8, 3, 3, '2023-07-05 11:16:04', 'Thích bình luận của bạn!', 4),
(9, 1, 1, '2023-07-05 11:16:41', 'Thích bình luận của bạn!', 4),
(10, 1, 3, '2023-07-05 11:17:24', 'Thích bình luận của bạn!', 4),
(11, 3, 1, '2023-07-05 13:49:07', 'Gửi yêu cầu vào nhóm!', 1),
(12, 3, 2, '2023-07-05 13:49:42', 'Gửi yêu cầu vào nhóm!', 1),
(13, 2, 1, '2023-07-05 13:49:51', 'Gửi yêu cầu vào nhóm!', 1),
(14, 2, 1, '2023-07-05 13:51:27', 'Mời bạn vào nhóm !', 3),
(15, 3, 1, '2023-07-05 13:51:33', 'Mời bạn vào nhóm !', 3),
(16, 2, 1, '2023-07-05 13:51:39', 'Mời bạn vào nhóm !', 3),
(17, 2, 3, '2023-07-05 13:51:42', 'Mời bạn vào nhóm !', 3),
(18, 3, 1, '2023-07-05 13:55:51', 'Mời bạn vào nhóm !', 3),
(19, 3, 3, '2023-07-05 14:03:03', 'Gửi yêu cầu kết bạn!', 0),
(20, 1, 3, '2023-07-05 14:03:07', 'Gửi yêu cầu kết bạn!', 0),
(21, 3, 2, '2023-07-05 14:03:15', 'Gửi yêu cầu kết bạn!', 0),
(22, 3, 3, '2023-07-05 14:03:22', 'Gửi yêu cầu kết bạn!', 0),
(23, 1, 1, '2023-07-05 14:04:00', 'Gửi yêu cầu kết bạn!', 0),
(24, 1, 1, '2023-07-05 14:06:23', 'Gửi yêu cầu kết bạn!', 0);

-- --------------------------------------------------------

--
-- Table structure for table `messager`
--

CREATE TABLE `messager` (
  `id` bigint NOT NULL,
  `from_user_id` bigint DEFAULT NULL,
  `to_user_id` bigint DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `chat_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `messager`
--

INSERT INTO `messager` (`id`, `from_user_id`, `to_user_id`, `content`, `date_created`, `chat_id`) VALUES
(1, 3, 2, 'test', '2023-07-05 11:35:35', 1),
(2, 1, 2, 'sned-test', '2023-07-05 11:36:13', 2),
(3, 1, 3, 'sned-noline', '2023-07-05 11:36:27', 3);

-- --------------------------------------------------------

--
-- Table structure for table `notification_add_friends`
--

CREATE TABLE `notification_add_friends` (
  `id` bigint NOT NULL,
  `from_user_id` bigint DEFAULT NULL,
  `to_user_id` bigint DEFAULT NULL,
  `status` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `notification_add_friends`
--

INSERT INTO `notification_add_friends` (`id`, `from_user_id`, `to_user_id`, `status`) VALUES
(2, 1, 3, 0),
(3, 3, 2, 0),
(5, 1, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `notification_add_groups`
--

CREATE TABLE `notification_add_groups` (
  `id` bigint NOT NULL,
  `from_user_id` bigint DEFAULT NULL,
  `to_user_id` bigint DEFAULT NULL,
  `group_id` bigint DEFAULT NULL,
  `status` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `notification_add_groups`
--

INSERT INTO `notification_add_groups` (`id`, `from_user_id`, `to_user_id`, `group_id`, `status`) VALUES
(2, 3, 2, 2, 0),
(4, 2, 1, 3, 1),
(5, 3, 1, 9, 1),
(6, 2, 1, 1, 1),
(8, 3, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `notification_check_status`
--

CREATE TABLE `notification_check_status` (
  `id` bigint NOT NULL,
  `admin_id` bigint DEFAULT NULL,
  `post_group_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `notification_check_status`
--

INSERT INTO `notification_check_status` (`id`, `admin_id`, `post_group_id`) VALUES
(1, 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `post_group`
--

CREATE TABLE `post_group` (
  `id` bigint NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `status_check` bit(1) NOT NULL,
  `group_id` bigint DEFAULT NULL,
  `user_info_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `post_group`
--

INSERT INTO `post_group` (`id`, `content`, `date_created`, `status_check`, `group_id`, `user_info_id`) VALUES
(1, 'content-content-2', '2023-07-05 09:13:54', b'1', 1, 1),
(2, 'content-content-2', '2023-07-05 09:17:38', b'1', 2, 1),
(3, 'content-content-x', '2023-07-05 09:20:12', b'0', 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `post_user`
--

CREATE TABLE `post_user` (
  `id` bigint NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `status_id` bigint DEFAULT NULL,
  `user_info_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `post_user`
--

INSERT INTO `post_user` (`id`, `content`, `date_created`, `status_id`, `user_info_id`) VALUES
(1, 'content-content', '2023-07-05 08:46:02', NULL, 1),
(3, 'content-content-1', '2023-07-05 08:49:08', NULL, 1),
(4, 'content-content-2xxx', '2023-07-05 08:51:27', NULL, 1),
(5, 'test1 đã thay đổi ảnh nền', '2023-07-05 10:06:03', NULL, 2),
(6, 'admin1 đã thay đổi ảnh nền', '2023-07-05 10:10:06', NULL, 3),
(7, 'admin1 đã thay đổi ảnh đại diện', '2023-07-05 10:12:04', NULL, 3);

-- --------------------------------------------------------

--
-- Table structure for table `reply_comment`
--

CREATE TABLE `reply_comment` (
  `id` bigint NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `user_info_comment_id` bigint DEFAULT NULL,
  `comment_id` bigint DEFAULT NULL,
  `post_user_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `reply_comment`
--

INSERT INTO `reply_comment` (`id`, `content`, `date_created`, `user_info_comment_id`, `comment_id`, `post_user_id`) VALUES
(1, 'puttest-reply-comment', '2023-07-05 10:50:29', 2, 6, 3),
(2, 'test-reply-comment', '2023-07-05 10:52:14', 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `status_post`
--

CREATE TABLE `status_post` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `block_status` bit(1) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `block_status`, `create_date`) VALUES
(1, 'admin', '$2a$10$ZF6a3/Wwo1OL9iJCkgcjTeL.hfTOd9HG1NGeoy7yyHcdjBll99tRK', NULL, NULL),
(2, 'test', '$2a$10$iXlFPIQfxJcoFwyeN8w40ezitkSZwMZh0eqh7aX9yoU3mAQkDXGr2', b'0', '2023-07-04 09:16:11'),
(3, 'test1', '$2a$10$iXlFPIQfxJcoFwyeN8w40ezitkSZwMZh0eqh7aX9yoU3mAQkDXGr2', b'0', '2023-07-04 09:16:43'),
(4, NULL, '$2a$10$yGuItdAfcWKmgxcZalEt2OP415BE.dJpZUutac0CQ2cKKOynI0V1C', b'0', '2023-07-14 13:38:01');

-- --------------------------------------------------------

--
-- Table structure for table `user_info`
--

CREATE TABLE `user_info` (
  `id` bigint NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `date_of_birth` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `back_ground` varchar(255) DEFAULT NULL,
  `interest` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user_info`
--

INSERT INTO `user_info` (`id`, `email`, `full_name`, `phone_number`, `date_of_birth`, `address`, `avatar`, `back_ground`, `interest`, `user_id`) VALUES
(1, 'test@gmail.com', 'test', '012232435', '11/11/2000', NULL, '1651410713778facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg', '1651390542985emirates-arsenal_gkpg.png', NULL, 2),
(2, 'test1@gmail.com', 'test1', '09123567000', '11/11/2000', 'HCM', '1651410713778facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg', 'cuu-thi-sinh-olympia.jpg1688526362762', NULL, 3),
(3, 'admin@gmail.com', 'admin1', '09123567000', '11/11/2001', 'HCM', '1688526724422img_girl.jpg', 'Anh-hai-huoc-dua-xe-dap.jpg1688526605816', NULL, 1),
(4, 'test1@gmail.com', NULL, '0908232435', '11/11/2000', NULL, '1651410713778facebook-cap-nhat-avatar-doi-voi-tai-khoan-khong-su-dung-anh-dai-dien-e4abd14d.jpg', '1651390542985emirates-arsenal_gkpg.png', NULL, 4);

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `user_id` bigint NOT NULL,
  `roles_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`user_id`, `roles_id`) VALUES
(1, 1),
(2, 2),
(3, 2),
(4, 2);

-- --------------------------------------------------------

--
-- Table structure for table `video_post_user`
--

CREATE TABLE `video_post_user` (
  `id` bigint NOT NULL,
  `video` varchar(255) DEFAULT NULL,
  `post_user_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chat`
--
ALTER TABLE `chat`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_CHAT_ON_USER1` (`user1_id`),
  ADD KEY `FK_CHAT_ON_USER2` (`user2_id`);

--
-- Indexes for table `chat1`
--
ALTER TABLE `chat1`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_CHAT1_ON_RECEIVER` (`receiver_id`),
  ADD KEY `FK_CHAT1_ON_SENDER` (`sender_id`);

--
-- Indexes for table `comment_post_user`
--
ALTER TABLE `comment_post_user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_COMMENTPOSTUSER_ON_POSTUSER` (`post_user_id`),
  ADD KEY `FK_COMMENTPOSTUSER_ON_USERINFOPOST` (`user_info_post_id`);

--
-- Indexes for table `friendship`
--
ALTER TABLE `friendship`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_FRIENDSHIP_ON_FROM_USER` (`from_user_id`),
  ADD KEY `FK_FRIENDSHIP_ON_TO_USER` (`to_user_id`);

--
-- Indexes for table `groups_`
--
ALTER TABLE `groups_`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_GROUPS__ON_GROUPSTATUS` (`group_status_id`),
  ADD KEY `FK_GROUPS__ON_USER` (`user_id`);

--
-- Indexes for table `group_members`
--
ALTER TABLE `group_members`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_GROUPMEMBERS_ON_GROUP` (`group_id`),
  ADD KEY `FK_GROUPMEMBERS_ON_USERINFO` (`user_info_id`);

--
-- Indexes for table `group_status`
--
ALTER TABLE `group_status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `image_post_group`
--
ALTER TABLE `image_post_group`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_IMAGEPOSTGROUP_ON_POSTGROUP` (`post_group_id`);

--
-- Indexes for table `image_post_user`
--
ALTER TABLE `image_post_user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_IMAGEPOSTUSER_ON_POSTUSER` (`post_user_id`);

--
-- Indexes for table `like_comment`
--
ALTER TABLE `like_comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_LIKECOMMENT_ON_COMMENTPOSTUSER` (`comment_post_user_id`),
  ADD KEY `FK_LIKECOMMENT_ON_FROMUSER` (`from_user_id`);

--
-- Indexes for table `like_post_user`
--
ALTER TABLE `like_post_user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_LIKEPOSTUSER_ON_POSTUSER` (`post_user_id`),
  ADD KEY `FK_LIKEPOSTUSER_ON_USERINFO` (`user_info_id`);

--
-- Indexes for table `list_friend`
--
ALTER TABLE `list_friend`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_LISTFRIEND_ON_FRIENDSOFUSERINFO` (`friends_of_userinfo_id`),
  ADD KEY `FK_LISTFRIEND_ON_USERINFO` (`user_info_id`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_MESSAGE_ON_FROMUSER` (`from_user_id`),
  ADD KEY `FK_MESSAGE_ON_TOUSER` (`to_user_id`);

--
-- Indexes for table `messager`
--
ALTER TABLE `messager`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_MESSAGER_ON_CHAT` (`chat_id`),
  ADD KEY `FK_MESSAGER_ON_FROMUSER` (`from_user_id`),
  ADD KEY `FK_MESSAGER_ON_TOUSER` (`to_user_id`);

--
-- Indexes for table `notification_add_friends`
--
ALTER TABLE `notification_add_friends`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_NOTIFICATIONADDFRIENDS_ON_FROMUSER` (`from_user_id`),
  ADD KEY `FK_NOTIFICATIONADDFRIENDS_ON_TOUSER` (`to_user_id`);

--
-- Indexes for table `notification_add_groups`
--
ALTER TABLE `notification_add_groups`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_NOTIFICATIONADDGROUPS_ON_FROMUSER` (`from_user_id`),
  ADD KEY `FK_NOTIFICATIONADDGROUPS_ON_GROUP` (`group_id`),
  ADD KEY `FK_NOTIFICATIONADDGROUPS_ON_TOUSER` (`to_user_id`);

--
-- Indexes for table `notification_check_status`
--
ALTER TABLE `notification_check_status`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_NOTIFICATIONCHECKSTATUS_ON_ADMIN` (`admin_id`),
  ADD KEY `FK_NOTIFICATIONCHECKSTATUS_ON_POSTGROUP` (`post_group_id`);

--
-- Indexes for table `post_group`
--
ALTER TABLE `post_group`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_POSTGROUP_ON_GROUP` (`group_id`),
  ADD KEY `FK_POSTGROUP_ON_USERINFO` (`user_info_id`);

--
-- Indexes for table `post_user`
--
ALTER TABLE `post_user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_POSTUSER_ON_STATUS` (`status_id`),
  ADD KEY `FK_POSTUSER_ON_USERINFO` (`user_info_id`);

--
-- Indexes for table `reply_comment`
--
ALTER TABLE `reply_comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_REPLYCOMMENT_ON_COMMENT` (`comment_id`),
  ADD KEY `FK_REPLYCOMMENT_ON_POSTUSER` (`post_user_id`),
  ADD KEY `FK_REPLYCOMMENT_ON_USERINFOCOMMENT` (`user_info_comment_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `status_post`
--
ALTER TABLE `status_post`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_info`
--
ALTER TABLE `user_info`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_USERINFO_ON_USER` (`user_id`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD KEY `fk_user_role_on_role` (`roles_id`),
  ADD KEY `fk_user_role_on_user` (`user_id`);

--
-- Indexes for table `video_post_user`
--
ALTER TABLE `video_post_user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_VIDEOPOSTUSER_ON_POSTUSER` (`post_user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chat`
--
ALTER TABLE `chat`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `chat1`
--
ALTER TABLE `chat1`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `comment_post_user`
--
ALTER TABLE `comment_post_user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `friendship`
--
ALTER TABLE `friendship`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `groups_`
--
ALTER TABLE `groups_`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `group_members`
--
ALTER TABLE `group_members`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `group_status`
--
ALTER TABLE `group_status`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `image_post_group`
--
ALTER TABLE `image_post_group`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `image_post_user`
--
ALTER TABLE `image_post_user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `like_comment`
--
ALTER TABLE `like_comment`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `like_post_user`
--
ALTER TABLE `like_post_user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `list_friend`
--
ALTER TABLE `list_friend`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `messager`
--
ALTER TABLE `messager`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `notification_add_friends`
--
ALTER TABLE `notification_add_friends`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `notification_add_groups`
--
ALTER TABLE `notification_add_groups`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `notification_check_status`
--
ALTER TABLE `notification_check_status`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `post_group`
--
ALTER TABLE `post_group`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `post_user`
--
ALTER TABLE `post_user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `reply_comment`
--
ALTER TABLE `reply_comment`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `status_post`
--
ALTER TABLE `status_post`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user_info`
--
ALTER TABLE `user_info`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `video_post_user`
--
ALTER TABLE `video_post_user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chat`
--
ALTER TABLE `chat`
  ADD CONSTRAINT `FK_CHAT_ON_USER1` FOREIGN KEY (`user1_id`) REFERENCES `user_info` (`id`),
  ADD CONSTRAINT `FK_CHAT_ON_USER2` FOREIGN KEY (`user2_id`) REFERENCES `user_info` (`id`);

--
-- Constraints for table `chat1`
--
ALTER TABLE `chat1`
  ADD CONSTRAINT `FK_CHAT1_ON_RECEIVER` FOREIGN KEY (`receiver_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_CHAT1_ON_SENDER` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `comment_post_user`
--
ALTER TABLE `comment_post_user`
  ADD CONSTRAINT `FK_COMMENTPOSTUSER_ON_POSTUSER` FOREIGN KEY (`post_user_id`) REFERENCES `post_user` (`id`),
  ADD CONSTRAINT `FK_COMMENTPOSTUSER_ON_USERINFOPOST` FOREIGN KEY (`user_info_post_id`) REFERENCES `user_info` (`id`);

--
-- Constraints for table `friendship`
--
ALTER TABLE `friendship`
  ADD CONSTRAINT `FK_FRIENDSHIP_ON_FROM_USER` FOREIGN KEY (`from_user_id`) REFERENCES `user_info` (`id`),
  ADD CONSTRAINT `FK_FRIENDSHIP_ON_TO_USER` FOREIGN KEY (`to_user_id`) REFERENCES `user_info` (`id`);

--
-- Constraints for table `groups_`
--
ALTER TABLE `groups_`
  ADD CONSTRAINT `FK_GROUPS__ON_GROUPSTATUS` FOREIGN KEY (`group_status_id`) REFERENCES `group_status` (`id`),
  ADD CONSTRAINT `FK_GROUPS__ON_USER` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`);

--
-- Constraints for table `group_members`
--
ALTER TABLE `group_members`
  ADD CONSTRAINT `FK_GROUPMEMBERS_ON_GROUP` FOREIGN KEY (`group_id`) REFERENCES `groups_` (`id`),
  ADD CONSTRAINT `FK_GROUPMEMBERS_ON_USERINFO` FOREIGN KEY (`user_info_id`) REFERENCES `user_info` (`id`);

--
-- Constraints for table `image_post_group`
--
ALTER TABLE `image_post_group`
  ADD CONSTRAINT `FK_IMAGEPOSTGROUP_ON_POSTGROUP` FOREIGN KEY (`post_group_id`) REFERENCES `post_group` (`id`);

--
-- Constraints for table `image_post_user`
--
ALTER TABLE `image_post_user`
  ADD CONSTRAINT `FK_IMAGEPOSTUSER_ON_POSTUSER` FOREIGN KEY (`post_user_id`) REFERENCES `post_user` (`id`);

--
-- Constraints for table `like_comment`
--
ALTER TABLE `like_comment`
  ADD CONSTRAINT `FK_LIKECOMMENT_ON_COMMENTPOSTUSER` FOREIGN KEY (`comment_post_user_id`) REFERENCES `comment_post_user` (`id`),
  ADD CONSTRAINT `FK_LIKECOMMENT_ON_FROMUSER` FOREIGN KEY (`from_user_id`) REFERENCES `user_info` (`id`);

--
-- Constraints for table `like_post_user`
--
ALTER TABLE `like_post_user`
  ADD CONSTRAINT `FK_LIKEPOSTUSER_ON_POSTUSER` FOREIGN KEY (`post_user_id`) REFERENCES `post_user` (`id`),
  ADD CONSTRAINT `FK_LIKEPOSTUSER_ON_USERINFO` FOREIGN KEY (`user_info_id`) REFERENCES `user_info` (`id`);

--
-- Constraints for table `list_friend`
--
ALTER TABLE `list_friend`
  ADD CONSTRAINT `FK_LISTFRIEND_ON_FRIENDSOFUSERINFO` FOREIGN KEY (`friends_of_userinfo_id`) REFERENCES `user_info` (`id`),
  ADD CONSTRAINT `FK_LISTFRIEND_ON_USERINFO` FOREIGN KEY (`user_info_id`) REFERENCES `user_info` (`id`);

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `FK_MESSAGE_ON_FROMUSER` FOREIGN KEY (`from_user_id`) REFERENCES `user_info` (`id`),
  ADD CONSTRAINT `FK_MESSAGE_ON_TOUSER` FOREIGN KEY (`to_user_id`) REFERENCES `user_info` (`id`);

--
-- Constraints for table `messager`
--
ALTER TABLE `messager`
  ADD CONSTRAINT `FK_MESSAGER_ON_CHAT` FOREIGN KEY (`chat_id`) REFERENCES `chat` (`id`),
  ADD CONSTRAINT `FK_MESSAGER_ON_FROMUSER` FOREIGN KEY (`from_user_id`) REFERENCES `user_info` (`id`),
  ADD CONSTRAINT `FK_MESSAGER_ON_TOUSER` FOREIGN KEY (`to_user_id`) REFERENCES `user_info` (`id`);

--
-- Constraints for table `notification_add_friends`
--
ALTER TABLE `notification_add_friends`
  ADD CONSTRAINT `FK_NOTIFICATIONADDFRIENDS_ON_FROMUSER` FOREIGN KEY (`from_user_id`) REFERENCES `user_info` (`id`),
  ADD CONSTRAINT `FK_NOTIFICATIONADDFRIENDS_ON_TOUSER` FOREIGN KEY (`to_user_id`) REFERENCES `user_info` (`id`);

--
-- Constraints for table `notification_add_groups`
--
ALTER TABLE `notification_add_groups`
  ADD CONSTRAINT `FK_NOTIFICATIONADDGROUPS_ON_FROMUSER` FOREIGN KEY (`from_user_id`) REFERENCES `user_info` (`id`),
  ADD CONSTRAINT `FK_NOTIFICATIONADDGROUPS_ON_GROUP` FOREIGN KEY (`group_id`) REFERENCES `groups_` (`id`),
  ADD CONSTRAINT `FK_NOTIFICATIONADDGROUPS_ON_TOUSER` FOREIGN KEY (`to_user_id`) REFERENCES `user_info` (`id`);

--
-- Constraints for table `notification_check_status`
--
ALTER TABLE `notification_check_status`
  ADD CONSTRAINT `FK_NOTIFICATIONCHECKSTATUS_ON_ADMIN` FOREIGN KEY (`admin_id`) REFERENCES `user_info` (`id`),
  ADD CONSTRAINT `FK_NOTIFICATIONCHECKSTATUS_ON_POSTGROUP` FOREIGN KEY (`post_group_id`) REFERENCES `post_group` (`id`);

--
-- Constraints for table `post_group`
--
ALTER TABLE `post_group`
  ADD CONSTRAINT `FK_POSTGROUP_ON_GROUP` FOREIGN KEY (`group_id`) REFERENCES `groups_` (`id`),
  ADD CONSTRAINT `FK_POSTGROUP_ON_USERINFO` FOREIGN KEY (`user_info_id`) REFERENCES `user_info` (`id`);

--
-- Constraints for table `post_user`
--
ALTER TABLE `post_user`
  ADD CONSTRAINT `FK_POSTUSER_ON_STATUS` FOREIGN KEY (`status_id`) REFERENCES `status_post` (`id`),
  ADD CONSTRAINT `FK_POSTUSER_ON_USERINFO` FOREIGN KEY (`user_info_id`) REFERENCES `user_info` (`id`);

--
-- Constraints for table `reply_comment`
--
ALTER TABLE `reply_comment`
  ADD CONSTRAINT `FK_REPLYCOMMENT_ON_COMMENT` FOREIGN KEY (`comment_id`) REFERENCES `comment_post_user` (`id`),
  ADD CONSTRAINT `FK_REPLYCOMMENT_ON_POSTUSER` FOREIGN KEY (`post_user_id`) REFERENCES `post_user` (`id`),
  ADD CONSTRAINT `FK_REPLYCOMMENT_ON_USERINFOCOMMENT` FOREIGN KEY (`user_info_comment_id`) REFERENCES `user_info` (`id`);

--
-- Constraints for table `user_info`
--
ALTER TABLE `user_info`
  ADD CONSTRAINT `FK_USERINFO_ON_USER` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `fk_user_role_on_role` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `fk_user_role_on_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `video_post_user`
--
ALTER TABLE `video_post_user`
  ADD CONSTRAINT `FK_VIDEOPOSTUSER_ON_POSTUSER` FOREIGN KEY (`post_user_id`) REFERENCES `post_user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
