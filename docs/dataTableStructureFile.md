# 数据表结构设计文档



*注意：*

*由于本开发人员经验并不丰富，而且该项目是兴趣导向而非商业目的，所以该文档可能随时更改并不记录改了什么。*

## 1，用户模块

用于登录和身份验证。

### 1.1，用户表（tb_user）

| 列名         | 类型        | 备注                             |
| ------------ | ----------- | -------------------------------- |
| id           | INT(10)     | 主键，无符号自增                 |
| email        | VARCHAR(20) | 邮箱，登录名                     |
| password     | VARCHAR(20) | 密码                             |
| user_name    | VARCHAR(20) | 昵称，随时更改                   |
| limits       | INT(5)      | 权限等级，站长，管理员，普通用户 |
| is_effective | bit         | 此账号是否有效                   |

### 1.2，用户操作日志表 （tb_user_log）

| 列名         | 类型        | 备注                |
| ------------ | ----------- | ------------------- |
| user_id      | INT(10)     | 用户id，来自tb_user |
| operate      | VARCHAR(40) | 操作：CRUD，登录    |
| operate_time | TIMESTAMP   | 操作时间            |

## 2，编辑模块(名字暂定)

编辑地图，人物卡，剧情描述等

### 2.1，地图

地图需要实现编辑、使用等功能。

#### 2.1.1，地图（tb_map）

| 列名            | 类型        | 备注                  |
| --------------- | ----------- | --------------------- |
| id              | BIGINT(19)  | 主键，无符号自增      |
| author_id       | INT(10)     | 制作者id，来自tb_user |
| map_name        | VARCHAR(20) | 地图名                |
| map_description | VARCHAR(40) | 地图描述              |
| length          | INT(5)      | 长度，默认20          |
| width           | INT(5)      | 宽度，默认20          |
| height          | INT(5)      | 高度，默认1           |
| create_time     | TIMESTAMP   | 创建时间              |
| update_time     | TIMESTAMP   | 修改时间              |

#### 2.1.2，地图道具 （tb_map_prop）

地图道具包括**地形**，**大型物品**（长椅，花坛等）和 **可拾起道具**等

| 列名             | 类型        | 备注                  |
| ---------------- | ----------- | --------------------- |
| id               | BIGINT(19)  | 主键，无符号自增      |
| author_id        | INT(10)     | 制作者id，来自tb_user |
| prop_name        | VARCHAR(20) | 道具名                |
| prop_description | VARCHAR(40) | 道具描述              |
| is_block         | BIT         | 是不是障碍物，默认F   |
| is_picked        | BIT         | 是否可拾起，默认F     |
| is_broken        | BIT         | 是否损坏，默认F       |
| create_time      | TIMESTAMP   | 创建时间              |
| update_time      | TIMESTAMP   | 修改时间              |

#### 2.1.3,地图内容表 （tb_map_details）

将  **一个地图上某个坐标**  和  **对应的道具** 关联起来

| 列名    | 类型       | 备注                     |
| ------- | ---------- | ------------------------ |
| id      | BIGINT(19) | 主键，无符号自增         |
| map_id  | BIGINT(19) | 属于哪个地图，来自tb_map |
| prop_id | BIGINT(19) | 道具id，来自tb_map_prop  |
| coord_l | INT(5)     | x坐标，对应地图的length  |
| coord_w | INT(5)     | y坐标，对应地图的width   |
| coord_h | INT(5)     | z坐标，对应地图的height  |



### 2.2人物卡

编辑人物

#### 2.2.1 基本信息和背景故事 表（tb_personal_info）

创建的人物基础信息表，包括了大部分不会改变的属性

**注：** 

- 表中未标出**时代**属性，该属性理应于每局游戏中定义，因此这个属性继承自kp定义的每局游戏中时代的属性。
- 表中不含**职业名**属性，该属性将根据职业信息id查找



| 列名                   | 类型         | NOT NULL | 备注                                                 |
| ---------------------- | ------------ | -------- | ---------------------------------------------------- |
| id                     | BIGINT(19)   | T        | 主键，无符号自增                                     |
| author_id              | INT(10)      | T        | 制作者id，来自tb_user                                |
| user_name              | VARCHAR(20)  | T        | 制作者用户名，来自tb_user(在有id的情况下是否有必要)  |
| personal_name          | VARCHAR(20)  | T        | 人物姓名                                             |
| personal_job_id        | BIGINT(19)   | T        | 职业信息id，来自tb_job(反正class里肯定是职业的class) |
| personal_age           | INT(5)       | T        | 人物年龄                                             |
| personal_gender        | INT(2)       | T        | 人物性别                                             |
| personal_address       | VARCHAR(20)  |          | 人物现住址                                           |
| personal_homeland      | VARCHAR(20)  |          | 人物故乡                                             |
| story_description      | VARCHAR(120) |          | 背景故事-人物描述                                    |
| story_thought          | VARCHAR(120) |          | 背景故事-思想和信念                                  |
| story_importance_man   | VARCHAR(120) |          | 背景故事-重要之人                                    |
| story_importance_place | VARCHAR(120) |          | 背景故事-意义非凡之地                                |
| story_importance_thing | VARCHAR(120) |          | 背景故事-宝贵之物                                    |
| story_peculiarity      | VARCHAR(120) |          | 背景故事_特质                                        |
| story_scar             | VARCHAR(120) |          | 背景故事_伤口和疤痕                                  |
| story_phobia           | VARCHAR(120) |          | 背景故事_恐惧症和躁狂症                              |

#### 2.2.2 角色属性表 tb_personal_property

存储调查员属性和格斗数据

| 列名         | 类型       | 备注                               |
| ------------ | ---------- | ---------------------------------- |
| id           | BIGINT(19) | 主键，无符号自增                   |
| personal_id  | BIGINT(19) | 属于哪个角色，来自tb_personal_info |
| personal_str | INT(5)     | 力量                               |
| personal_con | INT(5)     | 体质                               |
| personal_siz | INT(5)     | 体型                               |
| personal_dex | INT(5)     | 敏捷                               |
| personal_app | INT(5)     | 外貌                               |
| personal_int | INT(5)     | 智力                               |
| personal_pow | INT(5)     | 意志                               |
| personal_edu | INT(5)     | 教育                               |
| personal_mov | INT(5)     | 移动力                             |
| fight_damage | INT(5)     | 伤害加值                           |
| fight_build  | INT(5)     | 体格                               |
| fight_dodge  | INT(5)     | 闪避                               |
| fight_armor  | INT(5)     | 护甲                               |

#### 2.2.3 人物状态表tb_personal_status

包括人物当前状态和资产情况等易变动的数值

| 列名              | 类型        | 备注                               |
| ----------------- | ----------- | ---------------------------------- |
| id                | BIGINT(19)  | 主键，无符号自增                   |
| personal_id       | BIGINT(19)  | 属于哪个角色，来自tb_personal_info |
| personal_hp       | INT(5)      | 体力                               |
| personal_san      | INT(5)      | 理智                               |
| personal_luck     | INT(5)      | 外貌                               |
| personal_mp       | INT(5)      | 魔法值                             |
| personal_state    | VARCHAR(10) | 状态值,默认无状态                  |
| personal_health   | VARCHAR(10) | 健康状态，默认神志清醒             |
| asset_rating      | INT(5)      | 信用评级，为%                      |
| asset_level       | VARCHAR(10) | 生活水平                           |
| asset_cash        | INT(10)     | 现金                               |
| asset_consumption | INT(10)     | 消费水平                           |
| package_count     | INT(5)      | 当前背包内物品数量                 |

#### 2.2.4 人物背包信息tb_personal_package

包括**背包内道具**和**克苏鲁神话物品**、**第三类接触**、**调查员同伴关系**几项，因为内容相似所以合并为一张表。

| 列名             | 类型        | NOT NULL | 备注                                                       |
| ---------------- | ----------- | -------- | ---------------------------------------------------------- |
| id               | BIGINT(19)  | T        | 主键，无符号自增                                           |
| personal_id      | BIGINT(19)  | T        | 属于哪个角色，来自tb_personal_info                         |
| prop_name        | VARCHAR(20) | T        | 道具名                                                     |
| prop_description | VARCHAR(60) |          | 道具描述                                                   |
| prop_property    | VARCHAR(10) | T        | 道具属性(道具，克苏鲁神话物品，第三类接触，调查员同伴关系) |
| prop_count       | INT(5)      | T        | 道具数量/武器剩余次数（0为不可摧毁）                       |

#### 2.2.5 人物武器 tb_persional_weapon

武器信息，位于地上的武器拾起时确定信息

| 列名                | 类型        | NOT NULL | 备注                               |
| ------------------- | ----------- | -------- | ---------------------------------- |
| id                  | BIGINT(19)  | T        | 主键，无符号自增                   |
| personal_id         | BIGINT(19)  | T        | 属于哪个角色，来自tb_personal_info |
| weapon_name         | VARCHAR(20) | T        | 武器名                             |
| weapon_description  | VARCHAR(60) |          | 武器描述                           |
| weapon_success_rate | INT(5)      |          | 武器成功率                         |
| weapon_through      | INT(5)      |          | 武器贯穿                           |
| weapon_damage       | VARCHAR(10) |          | 武器伤害 如 “1D3 + DB”             |
| weapon_range        | INT(5)      |          | 武器射程                           |
| weapon_count        | INT(5)      | T        | 武器剩余次数（0为不可摧毁）        |
| weapon_ammo         | INT(5)      |          | 武器弹药（0为无限或不需要弹药      |
| weapon_fail         | INT(5)      |          | 故障值                             |

### 2.3 职业

#### 2.3.1 职业信息tb_profession

本职技能通过**id**去**tb_profession_skill**里找

| 列名                   | 类型         | NOT NULL | 备注                                                         |
| ---------------------- | ------------ | -------- | ------------------------------------------------------------ |
| id                     | BIGINT(19)   | T        | 主键，无符号自增                                             |
| author_id              | INT(10)      | T        | 制作者id，来自tb_user                                        |
| profession_name        | VARCHAR(20)  | T        | 职业名                                                       |
| profession_description | VARCHAR(120) |          | 描述                                                         |
| profession_min_rating  | INT(5)       | T        | 职业信用评级范围                                             |
| profession_max_rating  | INT(5)       | T        | 职业信用评级范围                                             |
| profession_skill_point | VARCHAR(20)  | T        | 本职技能点数计算方式，如    “教育 ×2 ＋敏捷或力量 ×2” 表示为 “EDU\*2+DEX/STR\*2" |
| profession_privy       | VARCHAR(20)  |          | 如"本地居民，土著，贸易商。"仅展示                           |

#### 2.3.2技能信息tb_skill

| 列名               | 类型         | NOT NULL | 备注                                                       |
| ------------------ | ------------ | -------- | ---------------------------------------------------------- |
| id                 | BIGINT(19)   | T        | 主键，无符号自增                                           |
| author_id          | INT(10)      | T        | 制作者id，来自tb_user                                      |
| skill_name         | VARCHAR(20)  | T        | 技能名                                                     |
| skill_description  | VARCHAR(120) |          | 描述                                                       |
| skill_class        | VARCHAR(120) | T        | 技能类别，分为通用、艺术与手艺、科学、格斗、射击、罕见技能 |
| skill_init         | INT(5)       | T        | 技能初始值                                                 |
| skill_growth       | INT(5)       | T        | 技能成长值                                                 |
| skill_interest     | INT(5)       | T        | 技能兴趣                                                   |
| skill_success_rate | INT(5)       | T        | 成功率                                                     |

#### 2.3.3 职业对应技能tb_profession_skill

| 列名          | 类型       | NOT NULL | 备注                      |
| ------------- | ---------- | -------- | ------------------------- |
| id            | BIGINT(19) | T        | 主键，无符号自增          |
| profession_id | BIGINT(19) | T        | 职业id，来自tb_profession |
| skill_id      | BIGINT(19) | T        | 技能id，来自tb_skill      |

### 2.4 kp的故事//TODO

这里我不知道该有啥

##  3，游戏模块

### 3.1游戏房间 tb_room

| 列名        | 类型       | NOT NULL | 备注                                              |
| ----------- | ---------- | -------- | ------------------------------------------------- |
| id          | BIGINT(19) | T        | 主键，无符号自增                                  |
| master_id   | INT(10)    | T        | 房主id，来自tb_user                               |
| room_stat   | VARCHAR(5) | T        | 房间状态：新建、准备投票、游戏中、挂起/暂停、解散 |
| create_time | TIMESTAMP  | T        | 创建时间                                          |
| begin_time  | TIMESTAMP  | T        | 开始时间                                          |
| curr_count  | INT(5)     | T        | 当前人数                                          |

### 3.2房间详情tb_room_detail	

| 列名            | 类型       | NOT NULL | 备注                                                         |
| --------------- | ---------- | -------- | ------------------------------------------------------------ |
| id              | BIGINT(19) | T        | 主键，无符号自增                                             |
| room_id         | BIGINT(19) | T        | 房间号                                                       |
| object_id       | BIGINT(19) | T        | 该条目本身id（如玩家id,角色id等                              |
| object_property | VARCHAR(5) | T        | 该条目属于什么：玩家、角色、地图                             |
| join_time       | TIMESTAMP  | T        | 加入时间                                                     |
| object_stat     | VARCHAR(5) |          | 当前状态: 玩家有（在线，离开，本次游戏中），角色有（使用中、死亡）等 |

### 3.3 游戏记录tb_room_log

可能是最大的表了

| 列名             | 类型         | NOT NULL | 备注                                                         |
| ---------------- | ------------ | -------- | ------------------------------------------------------------ |
| id               | BIGINT(19)   | T        | 主键，无符号自增（用于分表）                                 |
| room_id          | BIGINT(19)   | T        | 房间id                                                       |
| action_person_id | BIGINT(19)   | T        | 发起角色id                                                   |
| action_class     | VARCHAR(5)   | T        | 动作类型（如发言，攻击，治疗等                               |
| action_detail    | VARCHAR(120) | T        | 动作内容，格式如下:   **例1：** *刘二不瘦(玩家:刘二)使用徒手格斗向白瘦(玩家:白胖)进行攻击，造成了0点伤害！*        **例2：** *KP(玩家:刘二)发言：白瘦走向面试官，对面试官使用技能“说服”，检定结果：大成功！效果拔群，白瘦获得了这份年薪$1,000,000的工作。* |
| target_person_id | BIGINT(19)   |          | 目标角色id                                                   |
| action_time      | TIMESTAMP    | T        | 时间                                                         |

## 4，归档模块//TODO

此模块功能应为存储完成的游戏历史记录等功能...