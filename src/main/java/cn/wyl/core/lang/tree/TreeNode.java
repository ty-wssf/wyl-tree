package cn.wyl.core.lang.tree;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 默认节点实体
 *
 * @param <ID> ID类型
 * @author wyl
 * @since 2021-11-01 10:06:44
 */
public class TreeNode<ID> implements Node<ID> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private ID id;

    /**
     * 父节点ID
     */
    private ID parentId;

    /**
     * 名称
     */
    private CharSequence name;

    /**
     * 顺序 越小优先级越高 默认0
     */
    private Comparable<?> weight = 0;

    /**
     * 子节点列表
     */
    private List<TreeNode<ID>> children;


    /**
     * 扩展字段
     */
    private Map<String, Object> extra;


    /**
     * 空构造
     */
    public TreeNode() {
    }

    /**
     * 构造
     *
     * @param id       ID
     * @param parentId 父节点ID
     * @param name     名称
     * @param weight   权重
     */
    public TreeNode(ID id, ID parentId, String name, Comparable<?> weight) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        if (weight != null) {
            this.weight = weight;
        }

    }

    @Override
    public ID getId() {
        return id;
    }

    @Override
    public TreeNode<ID> setId(ID id) {
        this.id = id;
        return this;
    }

    @Override
    public ID getParentId() {
        return this.parentId;
    }

    @Override
    public TreeNode<ID> setParentId(ID parentId) {
        this.parentId = parentId;
        return this;
    }

    @Override
    public CharSequence getName() {
        return name;
    }

    @Override
    public TreeNode<ID> setName(CharSequence name) {
        this.name = name;
        return this;
    }

    @Override
    public Comparable<?> getWeight() {
        return weight;
    }

    @Override
    public TreeNode<ID> setWeight(Comparable<?> weight) {
        this.weight = weight;
        return this;
    }

    public List<TreeNode<ID>> getChildren() {
        return children;
    }

    public TreeNode<ID> setChildren(List<TreeNode<ID>> children) {
        this.children = children;
        return this;
    }

    /**
     * 获取扩展字段
     *
     * @return 扩展字段Map
     */
    public Map<String, Object> getExtra() {
        return extra;
    }

    /**
     * 设置扩展字段
     *
     * @param extra 扩展字段
     * @return this
     */
    public TreeNode<ID> setExtra(Map<String, Object> extra) {
        this.extra = extra;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TreeNode<?> treeNode = (TreeNode<?>) o;
        return Objects.equals(id, treeNode.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
