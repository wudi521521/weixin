package com.googltest;

/**
 * @author Wudi
 * @Description:
 * @date 16:42  2018/1/26
 */
public class TreeMain {

    //前序查询
    private void preOrder(TreeNode tree){
        if (tree == null){
            return;
        }
        System.out.print(tree.getValue());
        //使用递归进行获取
        preOrder(tree.getLeft());
        preOrder(tree.getRight());
    }
    //中序查询
    private void middleOrder(TreeNode tree){
        if (tree == null){
            return;
        }
        //使用递归进行获取
        preOrder(tree.getLeft());
        System.out.print(tree.getValue());
        preOrder(tree.getRight());

    }
    //后序
    private void afterOrder(TreeNode tree){
        if (tree == null){
            return;
        }

        //使用递归进行获取
        preOrder(tree.getLeft());
        preOrder(tree.getRight());
        System.out.print(tree.getValue());

    }

    public static void main(String[] args) {
        //先创建一棵树
        TreeCreator tree = new TreeCreator();
        TreeNode sampleTree = tree.createSampleTree();
        TreeMain main = new TreeMain();
        //树的前序查找
        main.preOrder(sampleTree);
        System.out.println();
        //中序查找
        main.middleOrder(sampleTree);
        System.out.println();
        //后序查找
        main.afterOrder(sampleTree);
        System.out.println();

        //后序查找

        //层次查找
    }
}
