
package com.myself.security.entity.main;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.myself.security.entity.IdEntity;

@Entity
@Table(name = "security_organization")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="com.myself.security.entity.main")
public class Organization extends IdEntity {

	/** 描述  */
	private static final long serialVersionUID = -7324011210610828114L;
	
	@Column(nullable=false, length=64)
	private String name;
	
	@Column(length=255)
	private String description;

	@ManyToOne
	@JoinColumn(name="parentId")
	private Organization parent;
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="parent")
	private List<Organization> children = Lists.newArrayList();
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="organization")
	private List<User> users = Lists.newArrayList();

	/**  
	 * 返回 name 的值   
	 * @return name  
	 */
	public String getName() {
		return name;
	}

	/**  
	 * 设置 name 的值  
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**  
	 * 返回 description 的值   
	 * @return description  
	 */
	public String getDescription() {
		return description;
	}

	/**  
	 * 设置 description 的值  
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**  
	 * 返回 parent 的值   
	 * @return parent  
	 */
	public Organization getParent() {
		return parent;
	}

	/**  
	 * 设置 parent 的值  
	 * @param parent
	 */
	public void setParent(Organization parent) {
		this.parent = parent;
	}

	/**  
	 * 返回 children 的值   
	 * @return children  
	 */
	public List<Organization> getChildren() {
		return children;
	}

	/**  
	 * 设置 children 的值  
	 * @param children
	 */
	public void setChildren(List<Organization> children) {
		this.children = children;
	}

	/**  
	 * 返回 users 的值   
	 * @return users  
	 */
	public List<User> getUsers() {
		return users;
	}

	/**  
	 * 设置 users 的值  
	 * @param users
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.addValue(id)
				.addValue(name)
				.addValue(parent == null ? null:parent.getName())
				.addValue(children.size())
				.toString();
	}
}
