package com.example.vo;

/**
 * 版本的相关的设置
 * @author Dontouch
 *
 */
public class Version {
	
	private boolean isnew;   //判断是否是新的
	private String version;
	private boolean force;
	private String url;
	
	//构造函数
	public Version(){
		
	}
	
	public Version(boolean isnew ,String version ,boolean force ,String url){
		super();
		this.isnew = isnew;
		this.version = version;
		this.force = force;
		this.url  = url;
		
	}
	
	public boolean isIsnew() {
		return isnew;
	}

	public void setIsnew(boolean isnew) {
		this.isnew = isnew;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public boolean isForce() {
		return force;
	}

	public void setForce(boolean force) {
		this.force = force;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
