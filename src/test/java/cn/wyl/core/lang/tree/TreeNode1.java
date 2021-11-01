package cn.wyl.core.lang.tree;

import lombok.Data;

import java.util.List;

/**
 * todo
 *
 * @author wyl
 * @since 2021-11-01 14:59:36
 */
@Data
class TreeNode1 {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 父节点ID
     */
    private Long parentId;

    /**
     * 名称
     */
    private CharSequence name;

    /**
     * 子节点列表
     */
    private List<TreeNode1> children;

    /**
     * 空构造
     */
    public TreeNode1() {
    }

    /**
     * 构造
     *
     * @param id       ID
     * @param parentId 父节点ID
     * @param name     名称
     * @param weight   权重
     */
    public TreeNode1(Long id, Long parentId, String name, Comparable<?> weight) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public TreeNode1 setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public TreeNode1 setParentId(Long parentId) {
        this.parentId = parentId;
        return this;
    }

    public CharSequence getName() {
        return name;
    }

    public TreeNode1 setName(CharSequence name) {
        this.name = name;
        return this;
    }

    public List<TreeNode1> getChildren() {
        return children;
    }

    public TreeNode1 setChildren(List<TreeNode1> children) {
        this.children = children;
        return this;
    }

}
