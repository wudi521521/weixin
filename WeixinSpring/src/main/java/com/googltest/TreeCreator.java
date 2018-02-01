package com.googltest;

/**
 * @author Wudi
 * @Description: 创建树
 * @date 16:34  2018/1/26
 */
public class TreeCreator {

    public TreeNode createSampleTree(){
        TreeNode root = new TreeNode('A');
        root.setLeft(new TreeNode('B'));
        root.getLeft().setLeft(new TreeNode('D'));
        root.getLeft().setRight(new TreeNode('E'));
        root.getLeft().getRight().setLeft(new TreeNode('G'));
        root.setRight(new TreeNode('C'));
       root.getRight().setRight(new TreeNode('F'));

        return root;
    }
}
