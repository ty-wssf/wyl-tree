package cn.wyl.core.lang.tree.parser;

import cn.wyl.core.lang.tree.TreeNode;

/**
 * 树节点解析器
 *
 * @param <T> 转换的实体 为数据源里的对象类型
 * @author wyl
 */
@FunctionalInterface
public interface NodeParser<T, ID> {
    /**
     * @param object 源数据实体
     * @return treeNode 树节点实体
     */
    void parse(T object, TreeNode<ID> treeNode);
}

