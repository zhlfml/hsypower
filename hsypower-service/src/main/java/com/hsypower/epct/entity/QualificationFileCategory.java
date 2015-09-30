package com.hsypower.epct.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hsypower.epct.BaseEntity;

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QualificationFileCategory extends BaseEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 1541328851349682371L;
	
	private String filePath;
	private CommonsMultipartFile file;
	private List<QualificationFile> qualificationFiles;

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
	 * @return the qualificationFiles
	 */
	@OrderBy
	@OneToMany(mappedBy = "qualificationFileCategory", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
	public List<QualificationFile> getQualificationFiles() {
		return qualificationFiles;
	}

	/**
	 * @param qualificationFiles the qualificationFiles to set
	 */
	public void setQualificationFiles(List<QualificationFile> qualificationFiles) {
		this.qualificationFiles = qualificationFiles;
	}
	
}
