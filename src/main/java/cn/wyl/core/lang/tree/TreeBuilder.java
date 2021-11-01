package cn.wyl.core.lang.tree;

import cn.hutool.core.builder.Builder;
import cn.wyl.core.lang.tree.parser.NodeParser;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 树构建器
 *
 * @param <ID> ID类型
 * @author wyl
 * @since 2021-11-01 09:15:15
 */
public class TreeBuilder<T, ID> implements Builder<List<T>> {

    private static final long serialVersionUID = 1L;
    private final Predicate<TreeNode<ID>> rootPredicate;
    // 组件树结构的原始数据
    private List<T> sourceList = new ArrayList<>();
    // 原始数据缓存
    private Map<ID, T> cache = new HashMap<>();
    // parentId,children
    private Map<ID, List<T>> treeCache = new HashMap<>();
    BiConsumer<T, List<T>> childConsumer = null;

    private Map<Integer, TreeNode<ID>> pairCache = new HashMap<>();

    /**
     * 构造
     *
     * @param rootPredicate 根节点断言
     */
    public TreeBuilder(Predicate<TreeNode<ID>> rootPredicate, BiConsumer<T, List<T>> childConsumer) {
        this.rootPredicate = rootPredicate;
        this.childConsumer = childConsumer;
    }

    /**
     * 创建Tree构建器
     *
     * @param rootPredicate
     * @param <ID>
     * @return
     */
    public static <T, ID> TreeBuilder<T, ID> of(Predicate<TreeNode<ID>> rootPredicate, BiConsumer<T, List<T>> childConsumer) {
        return new TreeBuilder(rootPredicate, childConsumer);
    }

    /**
     * 增加节点列表，增加的节点是不带子节点的
     *
     * @param list       Bean列表
     * @param nodeParser 节点转换器，用于定义一个Bean如何转换为Tree节点
     * @return this
     */
    public TreeBuilder<T, ID> append(List<T> list, NodeParser<T, ID> nodeParser) {
        Supplier<Stream<T>> streamSupplier = () -> list.stream();
        treeCache = streamSupplier.get()
                .peek(node -> {
                    TreeNode<ID> parseNode = null;
                    if (node instanceof TreeNode) {
                        parseNode = (TreeNode) node;
                    } else {
                        parseNode = new TreeNode<>();
                        nodeParser.parse(node, parseNode);
                    }
                    nodeParser.parse(node, parseNode);

                    int hash = System.identityHashCode(node);
                    pairCache.put(hash, parseNode);

                    sourceList.add(node);

                    cache.put(parseNode.getId(), node);
                })
                .filter(node -> {
                    return pairCache.get(System.identityHashCode(node)).getParentId() != null;
                })
                .collect(Collectors.groupingBy(node -> pairCache.get(System.identityHashCode(node)).getParentId()));
        return this;
    }

    @Override
    public List<T> build() {
        // 设置子节点
        Supplier<Stream<T>> streamSupplier = () -> sourceList.stream();
        return streamSupplier.get()
                //设置每个节点的子节点
                .peek(node -> {
                    int hash = System.identityHashCode(node);
                    childConsumer.accept(
                            cache.get(pairCache.get(hash).getId()),
                            treeCache.get(pairCache.get(hash).getId()) == null ? Collections.emptyList() : treeCache.get(pairCache.get(hash).getId()));
                })
                //获取根节点
                .filter(node -> {
                    int hash = System.identityHashCode(node);
                    return rootPredicate.test(pairCache.get(hash));
                })
                .collect(Collectors.toList());
    }

}
