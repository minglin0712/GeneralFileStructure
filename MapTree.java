/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maptree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author lin541019
 */
public class MapTree {
    
    private AtomicLong idCounter;
    private MindmapTree mindmapTree;
    
    public MapTree(){
        idCounter = idCounter = new AtomicLong();
        this.addNewTree();
    }
    
    public String getNewID(){
        return String.valueOf(idCounter.getAndIncrement());
    }
    
    public void addNewTree(){
        System.out.println("======Procedure Produce & Add Neurons======");
        
        //給定一個root
        Neuron rootNeuron = new Neuron(getNewID(), null, "I'm root");
        Neuron Neuron1 = new Neuron(getNewID(), rootNeuron.getID(), "I'm level1-1");
        Neuron Neuron2 = new Neuron(getNewID(), rootNeuron.getID(), "I'm level1-2");
        Neuron Neuron3 = new Neuron(getNewID(), rootNeuron.getID(), "I'm level1-3");
        Neuron Neuron4 = new Neuron(getNewID(), Neuron1.getID(), "I'm level1-1-1");
        Neuron Neuron5 = new Neuron(getNewID(), Neuron1.getID(), "I'm level1-1-2");
        Neuron Neuron6 = new Neuron(getNewID(), Neuron2.getID(), "I'm level1-2-1");
        //實作一個模擬的general mindmap tree
        mindmapTree = new MindmapTree(rootNeuron);
        mindmapTree.addNeuron(Neuron1);
        mindmapTree.addNeuron(Neuron2);
        mindmapTree.addNeuron(Neuron3);
        mindmapTree.addNeuron(Neuron4);
        mindmapTree.addNeuron(Neuron5);
        mindmapTree.addNeuron(Neuron6);
        mindmapTree.printNeurons();
        //創造一個MapTree
        mindmapTree.CreateMindmapTree();
        //走訪tree所有node
        mindmapTree.TraversalStart();
        
        System.out.println("======Procedure Move Neurons======");
        mindmapTree.moveNeuron(Neuron4, Neuron3);
        
        System.out.println("======Procedure Delete Neurons======");
        mindmapTree.deleteNeuron(Neuron2);
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new MapTree();
        
    }
}

class Neuron{
        private String content = null;
        private String id, linkid;
        
        public void setContent(String id, String content){
            this.id = id;
            this.content = content;
        }
        
        public void setLinkID(String id){
            this.linkid = id;
        }
        
        public String getLinkID(){return linkid;}
        
        public Neuron(String id, String content){
            this.setContent(id, content);
            //System.out.println("Set a new Neuron, ID: " + this.id + ", Content: " + this.content);
        }
        
        public Neuron(String id, String linkid, String content){
            this.setContent(id, content);
            this.setLinkID(linkid);
            //System.out.println("Set a new Neuron with LinkID, ID: " + 
              //      this.id + ", linkID: " + linkid + ", Content: " + this.content);
        }
        
        public String getID(){return id;}
        
        public String getContent(){return content;}
        
        public void LogNeuron(){
            System.out.println("Neuron information, id: " + 
                    this.id + ", linkID: " + linkid + ", Content: " + this.content);
        }
}

class MindmapTree{
    private ArrayList<Neuron> neurons;
    private ArrayList<TreeNode> mapTree;
    
    public void setNeurons(ArrayList<Neuron> neurons){
        neurons.clear();
        this.neurons = neurons;
        setNeuronsID(this.neurons);
    }
    //建立ID, LinkID 的對照陣列表
    ArrayList<String[]> neuronsID;
    public void setNeuronsID(ArrayList<Neuron> neurons){
        neuronsID.clear();
        for(Neuron neuron : neurons){
            String[] tmp = {neuron.getID(), neuron.getLinkID()}; 
            neuronsID.add(tmp);
        }
    }
    //建立與String[] 有關的 General tree
    public void CreateMindmapTree(){
        //清空map樹
        mapTree = new ArrayList();
        //將Neuron中的id, linkid 轉換為 一維 String[] 存放
        for(Neuron neuron : neurons){
            String[] tmp = {neuron.getID(), neuron.getLinkID()}; 
            //建造好每個相對具有 ID 的 treenode
            mapTree.add(new TreeNode(tmp));
        }
        
        System.out.println("======Procedure Link======");
        
        //link mapTree 中每個TreeNode
        for(TreeNode parent : mapTree){
            String[] pStr = (String[]) parent.getReference();
            for(TreeNode child : mapTree){
                String[] cStr = (String[]) child.getReference();
                if(cStr[1] != null && pStr[0].equals(cStr[1])){
                        link(parent, child);
                        //System.out.println("p: " + parent.getInfo() + ", c: " + child.getInfo());
                }
            }
        }
        
        //for(TreeNode node : mapTree)  System.out.println(node.getChildren().toString());
        
        
        System.out.println("Mindmap Tree Build Complete!");
    }
    
    //使用內建的方法建構mindmap tree，建立本樹的 root
    public MindmapTree(Neuron root){
        neurons = new ArrayList();
        neurons.add(root);
    }
    
    public MindmapTree(){
        neurons.clear();
    }
    
    public void addNeuron(Neuron neuron){
        neurons.add(neuron);
    }
    
    public boolean link(TreeNode parent, TreeNode child){
        if(parent ==null || child == null) return false;
        child.setParent(parent);
        parent.addChildNode(child);
        return true;
    }
    
    public boolean unLink(TreeNode parent, TreeNode child){
        if(parent ==null || child == null) return false;
        parent.removeChild(child);
        child.clearParent();
        return true;
    }
    
    public boolean printNeurons(){
        if(neurons.isEmpty()) return false;
        for(Neuron neuron : neurons)
            neuron.LogNeuron();
        return true;
    }
    
    public TreeNode getRootNode(){
        for(TreeNode t : mapTree){
            String[] str = (String[]) t.getReference();
            if(str[1] == null) return t;
        }
        return null;
    }
    
    public void moveNeuron(Neuron child, Neuron parent){
        if(parent.getID() != child.getID() && child.getID() != null){
            //revise the linkid of child
            child.setLinkID(parent.getID());
            System.out.println("Neuron move sucessfully");
            
            //重複之前動作，建立map tree
            CreateMindmapTree();
            //走訪tree所有node
            TraversalStart();
        }else
            System.out.println("Failed : Parent node occurs error!");
    }
    
    public void deleteNeuron(Neuron neuron){
        if(neuron.getLinkID() != null){
            for(Neuron n : neurons)
                if(n.getLinkID() == neuron.getID())
                    n.setLinkID(neuron.getLinkID());
            neurons.remove(neuron);
            System.out.println("Neuron delete sucessfully");
            
            //重複之前動作，建立map tree
            CreateMindmapTree();
            //走訪tree所有node
            TraversalStart();
        }else
            System.out.println("Failed : Root node cant be deleted!");
    }
    
    public void TraversalStart(){
        System.out.println("======Procedure Tree Traversal======");
        TreeTraversal(getRootNode(), "");
    }
    
    public void TreeTraversal(TreeNode root, String path){
        path += root.getInfo();
        for( Object child : root.getChildren())
            TreeTraversal((TreeNode) child, path);
        if(root.getChildren().isEmpty())
            System.out.println(path);
    }
}

class TreeNode{
        private TreeNode parent;
        private List children;
        private Object reference;
        
        public TreeNode(Object object){
            this.parent = null;
            this.children = new ArrayList();
            this.reference = object;
        }
        
        /**
        * remove node from tree
        */
        public void remove() {
            if (parent != null) {
                parent.removeChild(this);
            }
        }
        
        public void setParent(TreeNode parent){
            this.parent = parent;
        }
        
        public void clearParent(){
            if(this.parent != null)
                this.parent = null;
        }
        
        /**
        * remove child node
        * @param child
        */
        public void removeChild(TreeNode child) {
           if (children.contains(child))
              children.remove(child);
        }
        
        /**
        * add child node
        * @param child node to be added
        */
        public void addChildNode(TreeNode child) {
            child.parent = this;
            if (!children.contains(child))
                children.add(child);
        }
        
        public String getInfo(){
            //System.out.println(this.children.toString());
            return Arrays.toString( (String[]) reference );
        }
        /**
        * @return level = distance from root
        */
        public int getLevel() {
            int level = 0;
            TreeNode p = parent;
            while (p != null) {
                ++level;
                p = p.parent;
            }
            return level;
        }
            /**
             * @return List of children
             */
        public List getChildren() {
            return children;
        }

        /**
        * @return parent node
        */
        public TreeNode getParent() {
            return parent;
        }

        /**
        * @return reference object
        */
        public Object getReference() {
            return reference;
        }

        /**
        * set reference object
        * @param object reference
        */
        public void setReference(Object object) {
            reference = object;
        }    
}
   
    
