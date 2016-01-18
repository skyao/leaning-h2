CREATE TABLE score
(
  member_id bigint NOT NULL,
  score bigint, -- 积分值
  create_timestamp bigint, -- 创建时间戳
  update_timestamp bigint, -- 更新时间戳
  CONSTRAINT score_pk_member_id PRIMARY KEY (member_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE score
  OWNER TO postgres;
COMMENT ON TABLE score
  IS '积分主表';
COMMENT ON COLUMN score.score IS '积分值';
COMMENT ON COLUMN score.create_timestamp IS '创建时间戳';
COMMENT ON COLUMN score.update_timestamp IS '更新时间戳';


CREATE TABLE score_log
(
  member_id bigint, -- 用户ID
  amount bigint, -- 变动积分，有正负
  previous_score bigint, -- 操作前积分
  after_score bigint, -- 操作后积分
  create_stamp bigint, -- 创建时间戳
  platform character varying(10),
  io character varying(4), -- 收入in，支出out
  transcation_id bigint, -- 事务Id
  score_type character varying(10), -- 积分类型
  remark character varying(200), -- 备注
  id serial NOT NULL, -- 自增主键
  CONSTRAINT score_log_pk_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE score_log
  OWNER TO postgres;
COMMENT ON TABLE score_log
  IS '积分流水表';
COMMENT ON COLUMN score_log.member_id IS '用户ID';
COMMENT ON COLUMN score_log.amount IS '变动积分，有正负';
COMMENT ON COLUMN score_log.previous_score IS '操作前积分';
COMMENT ON COLUMN score_log.after_score IS '操作后积分';
COMMENT ON COLUMN score_log.create_stamp IS '创建时间戳';
COMMENT ON COLUMN score_log.io IS '收入in，支出out';
COMMENT ON COLUMN score_log.transcation_id IS '事务Id';
COMMENT ON COLUMN score_log.score_type IS '积分类型';
COMMENT ON COLUMN score_log.remark IS '备注';
COMMENT ON COLUMN score_log.id IS '自增主键';


-- Index: "score_log_memberId_index"

-- DROP INDEX "score_log_memberId_index";

CREATE INDEX "score_log_memberId_index"
  ON score_log
  USING btree
  (member_id);

-- Table: transcations

-- DROP TABLE transcations;

CREATE TABLE transcations
(
  member_id bigint, -- 用户ID
  transcation_type character varying(1),
  amount integer, -- 积分变化量
  timeout bigint, -- 超时时间
  id serial NOT NULL, -- 自增主键
  CONSTRAINT transcations_pk_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE transcations
  OWNER TO postgres;
COMMENT ON TABLE transcations
  IS '事务处理表';
COMMENT ON COLUMN transcations.member_id IS '用户ID';
COMMENT ON COLUMN transcations.amount IS '积分变化量';
COMMENT ON COLUMN transcations.timeout IS '超时时间';
COMMENT ON COLUMN transcations.id IS '自增主键';