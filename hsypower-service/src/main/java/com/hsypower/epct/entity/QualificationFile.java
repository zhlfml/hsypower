package com.hsypower.epct.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hsypower.epct.BaseEntity;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QualificationFile extends BaseEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = -334669018120318161L;

	private String filePath;
	private CommonsMultipartFile file;
	private QualificationFileCategory qualificationFileCategory;

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the file
	 */
	@Transient
	public CommonsMultipartFile getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

	/**
	 * @return the qualificationFileCategory
	 */
	@ManyToOne
	@JoinColumn(name = "qualificationFileCategoryId")
	public QualificationFileCategory getQualificationFileCategory() {
		return qualificationFileCategory;
	}

	/**
	 * @param qualificationFileCategory the qualificationFileCategory to set
	 */
	public void setQualificationFileCategory(
			QualificationFileCategory qualificationFileCategory) {
		this.qualificationFileCategory = qualificationFileCategory;
	}
	
}
