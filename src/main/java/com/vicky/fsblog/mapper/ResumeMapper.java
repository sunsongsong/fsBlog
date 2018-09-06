package com.vicky.fsblog.mapper;

import com.vicky.fsblog.entity.Resume;
import com.vicky.fsblog.framework.mapper.IMyMapper;

/**
 * 简历
 *
 * @author James
 */
public interface ResumeMapper extends IMyMapper<Resume> {

  String COLUMN_LIST = "resume.id,title,introduction,resume.gmt_create AS gmtCreate,resume.gmt_modified AS gmtModified";
}