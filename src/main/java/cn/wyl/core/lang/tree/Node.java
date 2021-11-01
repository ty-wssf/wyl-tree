package cn.wyl.core.lang.tree;

import cn.hutool.core.comparator.CompareUtil;

import java.io.Serializable;

/**
 * 节点接口，提供节点相关的的方法定义
 *
 * @author wyl
 * @since 2021-11-01 09:08:11
 */
public interface Node<ID> extends Comparable<Node<ID>>, Serializable {

    /**
     * 获取ID
     *
     * @return ID
     */
    ID getId();

    /**
     * 设置ID
     *
     * @param id ID
     * @return this
     */
    Node<ID> setId(ID id);

    /**
     * 获取父节点ID
     *
     * @return 父节点ID
     */
    ID getParentId();

    /**
     * 设置父节点ID
     *
     * @param parentId 父节点ID
     * @return this
     */
    Node<ID> setParentId(ID parentId);

    /**
     * 获取节点标签名称
     *
     * @return 节点标签名称
     */
    CharSequence getName();

    /**
     * 设置节点标签名称
     *
     * @param name 节点标签名称
     * @return this
     */
    Node<ID> setName(CharSequence name);

    /**
     * 获取权重
     *
     * @return 权重
     */
    Comparable<?> getWeight();

    /**
     * 设置权重
     *
     * @param weight 权重
     * @return this
     */
    Node<ID> setWeight(Comparable<?> weight);

    @SuppressWarnings({"unchecked", "rawtypes", "NullableProblems"})
    @Override
    default int compareTo(Node node) {
        if (null == node) {
            return 1;
        }
        final Comparable weight = this.getWeight();
        final Comparable weightOther = node.getWeight();
        return CompareUtil.compare(weight, weightOther);
    }
}
