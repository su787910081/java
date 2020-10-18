## 数据库

### 整体划分

> - Activiti的后台是有数据库的支持，所有的表都以ACT_开头。 第二部分是表示表的用途的两个字母标识。 用途也和服务的API对应。
>
>   > - `ACT_RE_*`
>   >
>   >   > 'RE'表示repository。 这个前缀的表包含了流程定义和流程静态资源 （图片，规则，等等）。
>   >
>   > - `ACT_RU_*`
>   >
>   >   > 'RU'表示runtime。 这些运行时的表，包含流程实例，任务，变量，异步任务，等运行中的数据。 Activiti只在流程实例执行过程中保存这些数据， 在流程结束时就会删除这些记录。 这样运行时表可以一直很小速度很快。
>   >
>   > - `ACT_ID_*`
>   >
>   >   > 'ID'表示identity。 这些表包含身份信息，比如用户，组等等。
>   >
>   > - `ACT_HI_*`
>   >
>   >   > 'HI'表示history。 这些表包含历史数据，比如历史流程实例， 变量，任务等等。
>   >
>   > - ACT_GE_*
>   >
>   >   > 通用数据， 用于不同场景下，如存放资源文件。 

### 表结构

##### 资源库流程规则表

###### `act_re_deployment`    

> - 部署信息表
>
> | 字段             | 解释说明-描述                                 | 类型         |
> | ---------------- | --------------------------------------------- | ------------ |
> | `ID_`              | 主键，部署ID                                  | varchar(64)  |
> | `NAME_`            | 部署名称，一般是对这个流程定义描述说明文字    | varchar(255) |
> | `CATEGORY_`        |                                               | varchar(255) |
> | `KEY_`             | 流程定义KEY，一般用该值来创建一个流程运行实例 | varchar(255) |
> | `TENANT_ID_`       | 租户ID                                        | varchar(255) |
> | `DEPLOY_TIME_`     | 时间                                          | timestamp(3) |
> | `ENGINE_VERSION_`  |                                               | varchar(255) |
> 

###### `act_re_model`       

> - 流程设计模型部署表
>
> | 字段                          | 解释说明-描述 | 类型          |
> | ----------------------------- | ------------- | ------------- |
> | `ID_`                           |               | varchar(64)   |
> | `REV_`                          |               | int(11)       |
> | `NAME_`                         |               | varchar(255)  |
> | `KEY_`                          |               | varchar(255)  |
> | `CATEGORY_`                     |               | varchar(255)  |
> | `CREATE_TIME_`                  |               | timestamp(3)  |
> | `LAST_UPDATE_TIME_`             |               | timestamp(3)  |
> | `VERSION_`                      |               | int(11)       |
> | `META_INFO_`                    |               | varchar(4000) |
> | `DEPLOYMENT_ID_`                |               | varchar(64)   |
> | `EDITOR_SOURCE_VALUE_ID_`       |               | varchar(64)   |
> | `EDITOR_SOURCE_EXTRA_VALUE_ID_` |               | varchar(64)   |
> | `TENANT_ID_`                    |               | varchar(255)  |
>
> 

###### `act_re_procdef `     

> - 流程定义数据表
>
> | 字段                      | 解释说明-描述 | 类型          |
> | ------------------------- | ------------- | ------------- |
> | `ID_`                     |               | varchar(64)   |
> | `REV_`                    |               | int(11)       |
> | `CATEGORY_`               |               | varchar(255)  |
> | `NAME_`                   | 流程名称      | varchar(255)  |
> | `KEY_`                    | 流程定义KEY   | varchar(255)  |
> | `VERSION_`                |               | int(11)       |
> | `DEPLOYMENT_ID_`          | 部署ID，对应流程部署表ID_字段 | varchar(64)   |
> | `RESOURCE_NAME_`          | 资源名称 | varchar(4000) |
> | `DGRM_RESOURCE_NAME_`     |               | varchar(4000) |
> | `DESCRIPTION_`            |               | varchar(4000) |
> | `HAS_START_FORM_KEY_`     |               | tinyint(4)    |
> | `HAS_GRAPHICAL_NOTATION_` |               | tinyint(4)    |
> | `SUSPENSION_STATE_`       |               | int(11)       |
> | `TENANT_ID`               |               | varchar(255)  |
> | `ENGINE_VERSION`          |               | varchar(255)  |
>
> 

##### 运行时数据库表

######  `act_ru_execution `    

> - 运行时流程执行实例表
>
 > | 字段            | 解释说明-描述 | 类型         |
 > | --------------- | ------------- | ------------ |
 > | `ID_`           |               | varchar(64)  |
 > | `REV_`          |               | int(11)      |
 > | `PROC_INST_ID_` |               | varchar(64)  |
 > | `BUSINESS_KEY_` |               | varchar(255) |
 > | `PARENT_ID_`    |               | varchar(64)  |
 > |`PROC_DEF_ID_`||varchar(64)         |
 > |`SUPER_EXEC_`||varchar(64)          |
 > |`ROOT_PROC_INST_ID_`||varchar(64)   |
 > |`ACT_ID_`||varchar(255)             |
 > |`IS_ACTIVE_`||tinyint(4)            |
 > |`IS_CONCURRENT_`||tinyint(4)        |
 > |`IS_SCOPE_`||tinyint(4)             |
 > |`IS_EVENT_SCOPE_`||tinyint(4)       |
 > |`IS_MI_ROOT_`||tinyint(4)           |
 > |`SUSPENSION_STATE_`||int(11)        |
 > |`CACHED_ENT_STATE_`||int(11)        |
 > |`TENANT_ID_`||varchar(255)          |
 > |`NAME_`||varchar(255)               |
 > |`START_TIME_`||datetime(3)          |
 > |`START_USER_ID_`||varchar(255)      |
 > |`LOCK_TIME_`||timestamp(3)          |
 > |`IS_COUNT_ENABLED_`||tinyint(4)     |
 > |`EVT_SUBSCR_COUNT_`||int(11)        |
 > |`TASK_COUNT_`||int(11)              |
 > |`JOB_COUNT_`||int(11)               |
 > |`TIMER_JOB_COUNT_`||int(11)         |
 > |`SUSP_JOB_COUNT_`||int(11)          |
 > |`DEADLETTER_JOB_COUNT_`||int(11)    |
 > |`VAR_COUNT_`||int(11)               |
 > |`ID_LINK_COUNT_`||int(11)           |
 >

######  `act_ru_identitylink`   

> -  运行时流程人员表，主要存储任务节点与参与者的相关信息
> -  任务办理人表(个人任务，组任务)
>
> | 字段 | 解释说明-描述 | 类型 |
> | ---- | ------------- | ---- |
> |`ID_`||varchar(64)              |
> |`REV_`||int(11)                 |
> |`GROUP_ID_`||varchar(255)       |
> |`TYPE_`|participant-参与者(个人/组任务)类型;candidate-候选者(组任务)|varchar(255)           |
> |`USER_ID_`||varchar(255)        |
> |`TASK_ID_`|任务ID|varchar(64)         |
> |`PROC_INST_ID_`|流程实例ID|varchar(64)    |
> |`PROC_DEF_ID_`|流程定义ID|varchar(64)     |
>
> 

######  `act_ru_task`         

> - 运行时任务节点表
>
 > | 字段 | 解释说明-描述 | 类型 |
 > | ---- | ------------- | ---- |
 > |`ID_`||varchar(64)             |
 > |`REV_`||int(11)                |
 > |`EXECUTION_ID_`||varchar(64)   |
 > |`PROC_INST_ID_`||varchar(64)   |
 > |`PROC_DEF_ID_`||varchar(64)    |
 > |`NAME_`||varchar(255)          |
 > |`PARENT_TASK_ID_`||varchar(64) |
 > |`DESCRIPTION_`||varchar(4000)  |
 > |`TASK_DEF_KEY_`||varchar(255)  |
 > |`OWNER_`||varchar(255)         |
 > |`ASSIGNEE_`||varchar(255)      |
 > |`DELEGATION_`||varchar(64)     |
 > |`PRIORITY_`||int(11)           |
 > |`CREATE_TIME_`||timestamp(3)   |
 > |`DUE_DATE_`||datetime(3)       |
 > |`CATEGORY_`||varchar(255)      |
 > |`SUSPENSION_STATE_`||int(11)   |
 > |`TENANT_ID_`||varchar(255)     |
 > |`FORM_KEY_`||varchar(255)      |
 > |`CLAIM_TIME_`||datetime(3)     |

 ######  `act_ru_variable`     
 >
 > -   运行时流程变量数据表
 >
 > | 字段 | 解释说明-描述 | 类型 |
 > | ---- | ------------- | ---- |
 > |`ID_`||varchar(64)               |
 > |`REV_`||int(11)                  |
 > |`TYPE_`||varchar(255)            |
 > |`NAME_`||varchar(255)            |
 > |`EXECUTION_ID_`||varchar(64)     |
 > |`PROC_INST_ID_`||varchar(64)     |
 > |`TASK_ID_`||varchar(64)          |
 > |`BYTEARRAY_ID_`||varchar(64)     |
 > |`DOUBLE_`||double                |
 > |`LONG_`||bigint(20)              |
 > |`TEXT_`||varchar(4000)           |
 > |`TEXT2_`||varchar(4000          |

##### 历史数据库表

######  `act_hi_actinst`      

> -   历史节点表
>
 > | 字段 | 解释说明-描述 | 类型 |
 > | ---- | ------------- | ---- |
 > |`ID_`||varchar(64)                 |
 > |`PROC_DEF_ID_`||varchar(64)        |
 > |`PROC_INST_ID_`||varchar(64)       |
 > |`EXECUTION_ID_`||varchar(64)       |
 > |`ACT_ID_`||varchar(255)            |
 > |`TASK_ID_`||varchar(64)            |
 > |`CALL_PROC_INST_ID_`||varchar(64)  |
 > |`ACT_NAME_`||varchar(255)          |
 > |`ACT_TYPE_`||varchar(255)          |
 > |`ASSIGNEE_`||varchar(255)          |
 > |`START_TIME_`||datetime(3)         |
 > |`END_TIME_`||datetime(3)           |
 > |`DURATION_`||bigint(20)            |
 > |`DELETE_REASON_`||varchar(4000)    |
 > |`TENANT_ID_`||varchar(255)         |

######  `act_hi_attachment`    

> -  历史附件表
>
 > | 字段 | 解释说明-描述 | 类型 |
 > | ---- | ------------- | ---- |
 > |`ID_`||varchar(64)           |
 > |`REV_`||int(11)              |
 > |`USER_ID_`||varchar(255)     |
 > |`NAME_`||varchar(255)        |
 > |`DESCRIPTION_`||varchar(4000)|
 > |`TYPE_`||varchar(255)        |
 > |`TASK_ID_`||varchar(64)      |
 > |`PROC_INST_ID_`||varchar(64) |
 > |`URL_`||varchar(4000)        |
 > |`CONTENT_ID_`||varchar(64)   |
 > |`TIME_`||datetime(3)         |

######  `act_hi_comment`    

> -   历史意见表
>
 > | 字段 | 解释说明-描述 | 类型 |
 > | ---- | ------------- | ---- |
 > |`ID_`||varchar(64)           |
 > |`TYPE_`||varchar(255)        |
 > |`TIME_`||datetime(3)         |
 > |`USER_ID_`||varchar(255)     |
 > |`TASK_ID_`||varchar(64)      |
 > |`PROC_INST_ID_`||varchar(64) |
 > |`ACTION_`||varchar(255)      |
 > |`MESSAGE_`||varchar(4000)    |
 > |`FULL_MSG_`||longblob        |

######  `act_hi_identitylink`    

> - 历史流程人员表
>
 > | 字段 | 解释说明-描述 | 类型 |
 > | ---- | ------------- | ---- |
 > |`ID_`||varchar(64)          |
 > |`GROUP_ID_`||varchar(255)   |
 > |`TYPE_`||varchar(255)       |
 > |`USER_ID_`||varchar(255)    |
 > |`TASK_ID_`||varchar(64)     |
 > |`PROC_INST_ID_`||varchar(64)|

######  `act_hi_detail`       

> - 历史详情表，提供历史变量的查询
> - 历史任务办理人表(个人任务，组任务)
>
> | 字段 | 解释说明-描述 | 类型 |
> | ---- | ------------- | ---- |
> |`ID_`||varchar(64)               |
> |`TYPE_`||varchar(255)            |
> |`PROC_INST_ID_`||varchar(64)     |
> |`EXECUTION_ID_`||varchar(64)     |
> |`TASK_ID_`||varchar(64)          |
> |`ACT_INST_ID_`||varchar(64)      |
> |`NAME_`||varchar(255)            |
> |`VAR_TYPE_`||varchar(255)        |
> |`REV_`||int(11)                  |
> |`TIME_`||datetime(3)             |
> |`BYTEARRAY_ID_`||varchar(64)     |
> |`DOUBLE_`||double                |
> |`LONG_`||bigint(20)              |
> |`TEXT_`||varchar(4000)           |
> |`TEXT2_`||varchar(4000)          |

######  `act_hi_procinst`      

> -  历史流程实例表
>
 > | 字段 | 解释说明-描述 | 类型 |
 > | ---- | ------------- | ---- |
 > |`ID_`||varchar(64)                         |
 > |`PROC_INST_ID_`||varchar(64)               |
 > |`BUSINESS_KEY_`||varchar(255)              |
 > |`PROC_DEF_ID_`||varchar(64)                |
 > |`START_TIME_`||datetime(3)                 |
 > |`END_TIME_`||datetime(3)                   |
 > |`DURATION_`||bigint(20)                    |
 > |`START_USER_ID_`||varchar(255)             |
 > |`START_ACT_ID_`||varchar(255)              |
 > |`END_ACT_ID_`||varchar(255)                |
 > |`SUPER_PROCESS_INSTANCE_ID_`||varchar(64)  |
 > |`DELETE_REASON_`||varchar(4000)            |
 > |`TENANT_ID_`||varchar(255)                 |
 > |`NAME_`||varchar(255)                      |

######  `act_hi_taskinst`       

> - 历史任务实例表
>
 > | 字段 | 解释说明-描述 | 类型 |
 > | ---- | ------------- | ---- |
 > |`ID_`||varchar(64)               |
 > |`PROC_DEF_ID_`||varchar(64)      |
 > |`TASK_DEF_KEY_`||varchar(255)    |
 > |`PROC_INST_ID_`||varchar(64)     |
 > |`EXECUTION_ID_`||varchar(64)     |
 > |`NAME_`||varchar(255)            |
 > |`PARENT_TASK_ID_`||varchar(64)   |
 > |`DESCRIPTION_`||varchar(4000)    |
 > |`OWNER_`||varchar(255)           |
 > |`ASSIGNEE_`||varchar(255)        |
 > |`START_TIME_`||datetime(3)       |
 > |`CLAIM_TIME_`||datetime(3)       |
 > |`END_TIME_`||datetime(3)         |
 > |`DURATION_`||bigint(20)          |
 > |`DELETE_REASON_`||varchar(4000)  |
 > |`PRIORITY_`||int(11)             |
 > |`DUE_DATE_`||datetime(3)         |
 > |`FORM_KEY_`||varchar(255)        |
 > |`CATEGORY_`||varchar(255)        |
 > |`TENANT_ID_`|租户ID|varchar(255)       |

######  `act_hi_varinst`        

> - 历史变量表
>
 > | 字段 | 解释说明-描述 | 类型 |
 > | ---- | ------------- | ---- |
 > |`ID_`||varchar(64)                 |
 > |`PROC_INST_ID_`||varchar(64)       |
 > |`EXECUTION_ID_`||varchar(64)       |
 > |`TASK_ID_`||varchar(64)            |
 > |`NAME_`||varchar(255)              |
 > |`VAR_TYPE_`||varchar(100)          |
 > |`REV_`||int(11)                    |
 > |`BYTEARRAY_ID_`||varchar(64)       |
 > |`DOUBLE_`||double                  |
 > |`LONG_`||bigint(20)                |
 > |`TEXT_`||varchar(4000)             |
 > |`TEXT2_`||varchar(4000)            |
 > |`CREATE_TIME_`||datetime(3)        |
 > |`LAST_UPDATED_TIME_`||datetime(3)  |

##### 组织机构表

######  `act_id_group`      

> - 用户组信息表

######  `act_id_info`        

> - 用户扩展信息表

######  `act_id_membership`  

> - 用户与用户组对应信息表

######  `act_id_user`      

> -  用户信息表

##### 通用数据表

######  `act_ge_bytearray`      

> - 二进制数据表
>
 > | 字段 | 解释说明-描述 | 类型 |
 > | ---- | ------------- | ---- |
 > |`ID_`||varchar(64)           |
 > |`REV_`||int(11)              |
 > |`NAME_`||varchar(255)        |
 > |`DEPLOYMENT_ID_`||varchar(64)|
 > |`BYTES_`||longblob           |
 > |`GENERATED_`||tinyint(4)     |

######  `act_ge_property`          

> - 属性数据表存储整个流程引擎级别的数据,初始化表结构时，会默认插入三条记录，
>
 > | 字段 | 解释说明-描述 | 类型 |
 > | ---- | ------------- | ---- |
 > |`NAME_`||varchar(64)   |
 > |`VALUE_`||varchar(300) |
 > |`REV_`||int(11)        |

 





