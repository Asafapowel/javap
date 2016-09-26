package easyFrame.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    private String text;
    private HashMap target;
    private String domId;
    private String iconCls;
    private String state;
	private HashMap attributes;
	private Long parentId;
	private HashSet<Menu> chidren;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public HashMap getTarget() {
		return target;
	}
	public void setTarget(HashMap target) {
		this.target = target;
	}
	public String getDomId() {
		return domId;
	}
	public void setDomId(String domId) {
		this.domId = domId;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public HashMap getAttributes() {
		return attributes;
	}
	public void setAttributes(HashMap attributes) {
		this.attributes = attributes;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "menu_child")
	public HashSet<Menu> getChidren() {
		return chidren;
	}
	public void setChidren(HashSet<Menu> chidren) {
		this.chidren = chidren;
	}
	

	
}
