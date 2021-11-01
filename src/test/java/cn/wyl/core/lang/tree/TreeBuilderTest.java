package cn.wyl.core.lang.tree;

import cn.wyl.core.lang.tree.parser.DefaultNodeParser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author wyl
 * @since 2021-11-01 10:02:07
 */
public class TreeBuilderTest {

    static List<TreeNode<Long>> all_menu = new ArrayList<>();
    static List<TreeNode1> all_menu1 = new ArrayList<>();

    static {
        /*
         * root
         *    /module-A
         *    	   /module-A-menu-1
         *    /module-B
         *    	   /module-B-menu-1
         *    	   /module-B-menu-2
         */
        all_menu.add(new TreeNode<>(1L, 0L, "root", 0L));
        all_menu.add(new TreeNode<>(2L, 1L, "module-A", 0L));
        all_menu.add(new TreeNode<>(3L, 1L, "module-B", 0L));
        all_menu.add(new TreeNode<>(4L, 2L, "module-A-menu-1", 0L));
        all_menu.add(new TreeNode<>(5L, 3L, "module-B-menu-1", 0L));
        all_menu.add(new TreeNode<>(6L, 3L, "module-B-menu-2", 0L));

        all_menu1.add(new TreeNode1(1L, 0L, "root", 0L));
        all_menu1.add(new TreeNode1(2L, 1L, "module-A", 0L));
        all_menu1.add(new TreeNode1(3L, 1L, "module-B", 0L));
        all_menu1.add(new TreeNode1(4L, 2L, "module-A-menu-1", 0L));
        all_menu1.add(new TreeNode1(5L, 3L, "module-B-menu-1", 0L));
        all_menu1.add(new TreeNode1(6L, 3L, "module-B-menu-2", 0L));
    }

    @Test
    public void testBuilder1() {
        Predicate<TreeNode<Long>> predicate = longTree -> {
            return longTree.getId() == 1L;
        };
        TreeBuilder<TreeNode<Long>, Long> treeBuilder = TreeBuilder.of(predicate, TreeNode::setChildren);
        List<TreeNode<Long>> list = treeBuilder.append(all_menu, new DefaultNodeParser<>()).build();
        System.out.println(list);
    }

    @Test
    public void testBuilder2() {
        Predicate<TreeNode<Long>> predicate = longTree -> {
            return longTree.getId() == 1L;
        };
        TreeBuilder<TreeNode1, Long> treeBuilder = TreeBuilder.of(predicate, TreeNode1::setChildren);
        List<TreeNode1> list = treeBuilder.append(all_menu1, (treeNode1, tree) -> {
            tree.setId(treeNode1.getId());
            tree.setParentId(treeNode1.getParentId());
        }).build();
        System.out.println(list);
    }

}

