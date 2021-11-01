package cn.wyl.core.lang.tree.parser;

import cn.wyl.core.lang.tree.TreeNode;

/**
 * 默认的简单转换器
 *
 * @param <ID> ID类型
 * @author wyl
 */
public class DefaultNodeParser<ID> implements NodeParser<TreeNode<ID>, ID> {

    @Override
    public void parse(TreeNode<ID> object, TreeNode<ID> treeNode) {
        treeNode = object;
    }

}
