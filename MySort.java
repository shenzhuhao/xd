package com.nsu.sort;

import org.junit.Test;




/**
 * 数组排序
 * @author Administrator
 * @version 1.0
 */
public class MySort {

    public static final int[] a={1,5,9,135,45,35,85,455,8,56,64,85};

    public static void exchange(int i,int j){
        int t=a[i];
        a[i]=a[j];
        a[j]=t;
    }

    public static void display(){
        for(int num:a){
            System.out.print(num+" ");
        }
        System.out.println();
    }

    /**
     * 冒泡
     */
    @Test
    public void run(){
        for(int i=0;i<a.length;i++){
            boolean isF=true;
            for(int j=0;j<a.length-1-i;j++){
                if(a[j]>a[j+1]){
                    isF=false;
                    exchange(j,j+1);
                }
            }
            if (isF ) {
                break;
            }
        }
        display();
    }

    /**
     * 选择
     */
    @Test
    public void run1(){
        for(int i=0;i<a.length;i++){
            int min=i;
            for(int j=i+1;j<a.length;j++){
                if(a[min]>a[j]){
                    min=j;
                }
            }
            exchange(min,i);
        }
        display();
    }
    /**
     * 插入1
     */
    @Test
    public void run2(){
        int k,temp;
        for(int i=1;i<a.length;i++){
            k=i;
            temp=a[i];
            while(k>0&&a[k-1]>temp){
                a[k]=a[k-1];
                k--;
            }
            a[k]=temp;
        }
        display();
    }
    /**
     * 归并
     */
    @Test
    public void run3(){
        int[] space=new int[a.length];
        mergeSort(space,a,0,a.length-1);
        display();
    }

    public void mergeSort(int[] space,int[] a,int left,int right){
        if (left>=right) {
            return ;
        }
        int mid=(left+right)/2;
        mergeSort(space,a,left,mid);
        mergeSort(space,a,mid+1,right);
        merge(space,a,left,right,mid);
    }

    public void merge(int[] space,int[] a,int left,int right,int mid){
        int aLow=left;
        int aLength=mid;
        int bLow=mid+1;
        int bLength=right;
        int n=right-left+1;
        int k=0;
        while(aLow<=aLength&&bLow<=bLength){
            if(a[aLow]<a[bLow]){
                space[k++]=a[aLow++];
            }else{
                space[k++]=a[bLow++];
            }
        }
        while(aLow<=aLength){
            space[k++]=a[aLow++];
        }
        while(bLow<=bLength){
            space[k++]=a[bLow++];
        }
        for(int i=0;i<n;i++){
            a[i+left]=space[i];
        }
    }


    /**
     *希尔排序1
     */
    @Test
    public void run4(){
        shellSort();
        display();
    }

    public void shellSort(){
        int h=1;
        while(h<a.length){
            h=h*3+1;
        }
        h=(h-1)/3;
        int inner,outer,temp;
        while(h>0){
            for(outer=h;outer<a.length;outer++){
                inner=outer;
                temp=a[outer];
                while(inner-h>0&&a[inner-h]>temp){
                    a[inner]=a[inner-h];
                    inner-=h;
                }
                a[inner]=temp;
            }
            h=(h-1)/3;
        }
    }

    /**
     *希尔排序2
     */
    @Test
    public void run5(){
        shellSort2();
        display();
    }

    public void shellSort2(){
        int h;
        for(h=a.length/2;h>0;h/=2){
            for(int in=h;in<a.length;in++){
                int temp=a[in];
                int b;
                for(b=in;b-h>0&&a[b-h]>temp;b-=h){
                    exchange(b,b-h );
                }
                a[b]=temp;
            }
        }

    }

    /**
     *插入排序2
     */
    @Test
    public void run6(){
        for(int i=1;i<a.length;i++){
            for(int j=i;j>0;j--){
                if(a[j]<a[j-1]){
                    exchange(j, j-1);
                }
            }
        }
        display();
    }

    /**
     *快速排序1
     */
    @Test
    public void run7(){
        quickSort(0,a.length-1);
        display();
    }

    public void quickSort(int left,int right){
        if (left>=right){
            return ;
        }
        int index=huafen(left,right,a[right]);
        quickSort(left, index-1);
        quickSort(index+1, right);
    }

    public int huafen(int left,int right,int num){
        int leftPtr=left-1;
        int rightPtr=right;
        while(true){
            while(a[++leftPtr]<num){}
            while(rightPtr>0&&a[--rightPtr]>num){}
            if (leftPtr>=rightPtr){
                break;
            }
            exchange(leftPtr, rightPtr);
        }
        exchange(leftPtr, right);
        return leftPtr;
    }


    /**
     *快速排序2
     */
    @Test
    public void run8(){
        quickSort2(0,a.length-1);
        display();
    }

    public void quickSort2(int left,int right){
        int size=right-left+1;
        if(size<=3){
            smallSort(left,right);
        }else{
            int num=midOf3(left,right);
            int index=huafen2(left,right,num);
            quickSort2(left, index-1);
            quickSort2(index+1, right);
        }
    }

    public int huafen2(int left,int right,int num){
        int leftPtr=left-1;
        int rightPtr=right-1;
        while(true){
            while(a[++leftPtr]<num){}
            while(a[--rightPtr]>num){}
            if(leftPtr>=rightPtr){
                break;
            }
            exchange(leftPtr, rightPtr);
        }
        exchange(leftPtr, right-1);
        return leftPtr;
    }

    public int midOf3(int left,int right){
        int mid=(left+right)/2;
        if(a[left]>a[right]){
            exchange(left, right);
        }
        if(a[mid]>a[right]){
            exchange(mid, right);
        }
        if(a[left]>a[mid]){
            exchange(left, mid);
        }
        exchange(mid, right-1);
        return a[right-1];
    }



    public void smallSort(int left,int right){
        int k,temp;
        for(int i=left+1;i<=right;i++){
            k=i;
            temp=a[i];
            while(k>left&&a[k-1]>temp){
                a[k]=a[k-1];
                k--;
            }
            a[k]=temp;
        }
    }

    /**
     *快速排序3
     */
    @Test
    public void run9(){
        quickSort3(0,a.length-1);
        display();
    }

    public void quickSort3(int left,int right){
        if(left>=right){
           return;
        }
        int[] res=part(left, right, a[right]);
        quickSort3(left, res[0]-1);
        quickSort3(res[1]+1,right );
    }

    public int[] part(int left,int right,int num){
        int leftPtr=left-1;
        int rightPtr=right+1;
        int cur=left;
        while(cur<rightPtr){
           if(a[cur]<num){
               exchange(cur++, ++leftPtr);
           }else if(a[cur]>num){
                exchange(cur, --rightPtr);
           }else{
               cur++;
           }
        }
        return new int[]{leftPtr+1,rightPtr-1};
    }


    /**
     *堆排序
     */
    @Test
    public void run10(){
        for(int i=a.length/2-1;i>=0;i--){
            down(i);
        }
        for(int i=a.length-1;i>=0;i--){
            a[i]=remove();
        }
        display();
    }


    public int size=a.length;

    public int remove(){
        int top=a[0];
        a[0]=a[--size];
        down(0);
        return top;
    }

    public void down(int index){
        int large;
        int temp=a[index];
        while(index<size/2){
            int left=2*index+1;
            int right=left+1;
            if(right<size&&a[right]>a[left]){
                large=right;
            }else{
                large=left;
            }
            if(a[large]<temp){
                break;
            }
            a[index]=a[large];
            index=large;
        }
        a[index]=temp;
    }


    /**
     *基数排序
     */
    @Test
    public void run11(){
        oddSort(3);
        display();
    }

    public void oddSort(int wei){
        int n=1;
        int newWei=1;
        int k=0;
        int[][] bucket=new int[10][a.length];
        int[] order=new int[10];
        while(newWei<=wei){
            for(int num:a){
                int i=(num/n)%10;
                bucket[i][order[i]]=num;
                order[i]++;
            }
            for(int i=0;i<order.length;i++){
                if(order[i]!=0){
                    for(int j=0;j<order[i];j++){
                        a[k++]=bucket[i][j];
                    }
                }
                order[i]=0;
            }
            n*=10;
            k=0;
            newWei++;
        }
    }






}
