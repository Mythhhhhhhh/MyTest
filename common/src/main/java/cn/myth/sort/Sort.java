package cn.myth.sort;

import java.util.*;

public class Sort {
    public static void print(int[] a){
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
    }
    public static void InsertSort(int[] A)//直接插入排序
    {
        int i,j;
        //从下标为1开始比较，知道数组的末尾
        for(i=1;i<A.length;i++)
        {
            //将要比较的元素，拿出待比较后再插入数组
            int temp=A[i];
            //若当前元素小于前一个
            for(j=i-1;j>=0&&temp<A[j];--j)
                A[j+1]=A[j];//向后挪位置
            A[j+1]=temp;
        }
    }
    public static void BinaryInsertSort(int[] A)//折半插入排序
    {
        int i,j,low,high,mid;
        for(i=1;i<A.length;i++)
        {
            int temp=A[i];
            low=0;high=i-1;//折半查找范围
            while(low<=high)//折半查找，默认递增有序
            {
                mid=low+(high-low)/2;
                if(A[mid]>temp) high=mid-1;//查找左半子表
                else low=mid+1;//查找右半子表
            }
            for(j=i-1;j>=high+1;j--)//high+1即为要插入的点
                A[j+1]=A[j];//向后挪位置
            A[high+1]=temp;
        }
    }
    public static  void ShellSort(int[] A)//希尔排序
    {
        // gap为步长，每次减为原来的一半
        int i,j,k,gap;
        for( gap=A.length/2;gap>0;gap/=2)
        {
            //共gap个组，每个组都执行直接插入排序
            for( i=0;i<gap;i++)
            {
                for ( j=i+gap;j<A.length;j+=gap)
                {
                    // 如果A[j]<A[j-gap]，则寻找a[j]位置，并将后面数据的位置都后移
                    int temp=A[j];
                    for(k=j-gap;k>=0 && A[k]>temp;k-=gap)
                        A[k+gap]=A[k];
                    A[k+gap]=temp;
                }
            }
        }
    }
    public static void BubbleSort(int[] A) //冒泡排序
    {
        int i, j;
        for (i = 0; i < A.length - 1; i++)
            for (j = 0; j < A.length - i - 1; j++)
            {
                if (A[j] > A[j + 1])
                {
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                }
            }
    }
    public static void BubbleSort2(int[] A) //冒泡排序改进1
    {
        int i, j;
        for (i = 0; i < A.length - 1; i++)
        {
            boolean isSorted=true;
            for (j = 0; j < A.length - i - 1; j++)
            {
                if (A[j] > A[j + 1])
                {
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                    isSorted = false;
                }
            }
            if(isSorted==true) break;
        }
    }
    public static void BubbleSort3(int[] A)//冒泡排序改进2
    {
        int i, j;
        int lastExchangeIndex=0;//最后一次元素交换的位置
        int sortBorder=A.length-1;
        for (i = 0; i < A.length - 1; i++)
        {
            boolean isSorted=true;
            for (j = 0; j < sortBorder; j++)
            {
                if (A[j] > A[j + 1])
                {
                    int temp = A[j];
                    A[j] = A[j + 1];
                    A[j + 1] = temp;
                    isSorted = false;
                    lastExchangeIndex=j;
                }
            }
            sortBorder=lastExchangeIndex;
            if(isSorted==true) break;
        }
    }
    //-------------------------------快速排序-------------------------------------//
    public static void QuickSort(int[] A)//快速排序
    {
            QuickSort(A,0,A.length-1);
    }
    public static void QuickSort(int[] A,int low,int high)
    {
        int k=Partition6(A,low,high);
        if(k>low) QuickSort(A,low,k-1);
        if(k<high)QuickSort(A,k+1,high);
    }
    public static int Partition(int[] A,int low,int high) //左右指针法,枢纽值是第一个
    {
        int pivot=A[low];//将当前表中的第一个元素设置为枢纽值，对表进行划分
        int p=low;//枢纽的位置
        //如果枢纽值是第一个，要先从右走
        while(low<high)
        {
            while(A[high]>=pivot&&high>low) high--;
            while(A[low]<=pivot&&high>low) low++;
            if(low<high)
            {
                int temp=A[low];
                A[low]=A[high];
                A[high]=temp;
            }

        }
        int temp=A[p];
        A[p]=A[high];
        A[high]=temp;
        return high;//返回存放枢纽的最终位置
    }
    public static int Partition2(int[] A,int low,int high) //左右指针法,枢纽值是最后一个
    {
        int pivot=A[high];//将当前表中的第一个元素设置为枢纽值，对表进行划分
        int p=high;//枢纽的位置
        //如果枢纽值是最后一个，要先从左走
        while(low<high)
        {
            while(A[low]<=pivot&&high>low) low++;
            while(A[high]>=pivot&&high>low) high--;
            if(low<high)
            {
                int temp=A[low];
                A[low]=A[high];
                A[high]=temp;
            }

        }
        int temp=A[p];
        A[p]=A[low];
        A[low]=temp;
        return low;//返回存放枢纽的最终位置
    }
    public static int Partition3(int[] A,int low,int high) //挖坑法,枢纽值是第一个
    {
        int pivot=A[low];//将当前表中的第一个元素设置为枢纽值，对表进行划分
        //先从右走
        while(low<high)
        {
            while(A[high]>=pivot&&high>low) high--;
            A[low]=A[high];//比枢纽值小的元素移动到左端
            while(A[low]<=pivot&&high>low) low++;
            A[high]=A[low];//比枢纽值大的移动到右端
        }
        A[low]=pivot;//枢纽存放的最终位置
        return low;//返回存放枢纽的最终位置
    }
    public static int Partition4(int[] A,int low,int high) //挖坑法,枢纽值是最后一个
    {
        int pivot=A[high];//将当前表中的第一个元素设置为枢纽值，对表进行划分
        //先从左走
        while(low<high)
        {
            while(A[low]<=pivot&&high>low) low++;
            A[high]=A[low];//比枢纽值大的移动到右端
            while(A[high]>=pivot&&high>low) high--;
            A[low]=A[high];//比枢纽值小的元素移动到左端
        }
        A[high]=pivot;//枢纽存放的最终位置
        return high;//返回存放枢纽的最终位置
    }
    public static int Partition5(int[]A,int low,int high)//前后指针法
    {
        int pivot=A[high];//将当前表中的最后一个元素设置为枢纽值，对表进行划分
        int cur=low;
        int pre=low-1;
        while (cur<=high)
        {
            if(A[cur]<=pivot&&++pre!=cur)//先后顺序不能颠倒
            {
                //pre与cur交换
                int temp=A[pre];
                A[pre]=A[cur];
                A[cur]=temp;
            }
            cur++;
        }
        //若果是while(cur<high) 则需要执行下面注释的代码
        /*pre++;
        int temp=A[pre];
        A[pre]=A[high];
        A[high]=temp;
        */
        return pre;
    }
    public static int Partition6(int[]A,int low,int high)//前后指针法
    {
        int pivot=A[low];//将当前表中的第一个元素设置为枢纽值，对表进行划分
        int cur=low;
        int pre=low-1;
        while (cur<=high)
        {
            if(A[cur]<=pivot&&++pre!=cur)
            {
                //pre与cur交换
                int temp=A[pre];
                A[pre]=A[cur];
                A[cur]=temp;
            }
            cur++;
        }
        A[low]=A[pre];
        A[pre]=pivot;
        return pre;
    }
    public static void QuickSort2(int[] A)//快排非递归算法
    {
        QuickSort2(A,0,A.length-1);
    }
    public static void QuickSort2(int[] A,int low,int high)//快排非递归算法
    {
        Stack<Integer> s=new Stack<>();//用一个栈来保存空间
        s.push(low);//注意入栈的顺序
        s.push(high);//后入的high，所以要先拿high
        while(!s.isEmpty())
        {
            int hi=s.peek();
            s.pop();
            int lo=s.peek();
            s.pop();
            int index=Partition(A,lo,hi);
            if(index-1>lo)
            {
                s.push(lo);
                s.push(index-1);
            }
            if(index+1<hi)
            {
                s.push(index+1);
                s.push(hi);
            }
        }
    }
//------------------------------------------------------------------------//
    public static void SelectionSort(int[] A)//选择排序
    {
        int i,j,min;
        for(i=0;i<A.length-1;i++)
        {
            min=i;
            for(j=i+1;j<A.length;j++)
            {
                if(A[j]<A[min]) min=j;
            }
            if(min!=i)
            {
                int temp=A[min];
                A[min]=A[i];
                A[i]=temp;
            }
        }
    }
//----------------------------------堆排序--------------------------------//
    //大根堆
    public static void HeapSort(int[] A)
    {
        buildMaxHeap(A);
        for(int i=A.length-1;i>=1;i--)
        {
            int temp = A[0];  //将堆顶元素和堆低元素交换，即得到当前最大元素正确的排序位置
            A[0] = A[i];
            A[i] = temp;
            adjustHeap(A, 0,i-1);  //整理，将剩余的元素整理成堆
        }
    }
    public static void buildMaxHeap(int[] A)//构建大根堆
    //将A[]看出完全二叉树的顺序储存结构
    {
        //A[i]的左孩子是A[2*i+1],右孩子是A[2*i+2]
        //从第最后一个非叶子结点从下至上，从右至左调整结构
        for(int i=(A.length-2)/2;i>=0;i--)
        {
            adjustHeap(A,i,A.length-1);
        }
    }
    public static void adjustHeap(int[] A,int parent,int length)//非递归法
    {
        int temp=A[parent];//保存当前父节点
        for(int i=2*parent+1;i<=length;i=2*i+1)//i为初始化节点parent的左孩子，沿节点较大的子节点向下调整
        {
            if(i+1<=length&&A[i]<A[i+1])  //如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点的下标
                i++;                       //则选取右孩子结点的下标
            if(temp>=A[i]) //如果根节点大于或等于左右孩子中的较大者，则调整结束
                break;
            /*
                若是最小堆，则只需要把上面的A[i]<A[i+1]改成A[i+1]<A[i}
                                         temp>=A[i]改成temp<=A[i]
             */
            else//根节点小于左右孩子中的较大者
            {
                A[parent]=A[i];//将左右子结点中较大值A[i]调整到双亲节点上
                parent=i;//【关键】修改parent值，以便继续向下调整
            }
        }
        A[parent]=temp;//被调整的结点的值放人最终位置
    }

    public static void RadixSort(int[] A,int d)//基数排序
    {
        //d是位数 eg:如果A中最大数是111，即d=100
        //LSD
        int n=1;//代表位数对应的数:1,10,100,...
        int k=0;//保存每一位排序后的结果用于下一位的排序输入
        int[][] bucket=new int[10][A.length];//排序桶用于保存每次排序后的结果,这一位上排序结果相同的数字放在同一个桶里
        int[] order=new int[10];//用于保存每个桶里有多少个数字
        while (n<=d)
        {
            for(int i=0;i<A.length;i++)//将数组A里的每个数字放在相应的桶里
            {
                int digit=(A[i]/n)%10;
                bucket[digit][order[digit]]=A[i];
                order[digit]++;
            }
            for(int i=0;i<10;i++)//将前一个循环生成的桶里的数据覆盖到原数组中用于保存这一位的排序结果
            {
                if(order[i]!=0)//这个桶里有数据，从上到下遍历这个桶并将数据保存到原数组中,即先进桶的先出桶
                {
                    for(int j=0;j<order[i];j++)
                    {
                        A[k]=bucket[i][j];
                        k++;
                    }
                }
                order[i]=0;//将桶里计数器置0，用于下一次位排序
            }
            n=n*10;
            k=0;//将k置0，用于下一轮保存位排序结果
        }
    }

    public static void MergeSort(int[] A)//归并排序,递归
    {
        int []temp = new int[A.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        Sort(A,0,A.length-1,temp);
    }
    public static void Sort(int[] A,int low,int high,int[] temp)
    {
        if(low<high)
        {
            int mid=low+(high-low)/2;
            Sort(A,low,mid,temp);//左边归并排序，使得左子序列有序
            Sort(A,mid+1,high,temp);//右边归并排序，使得右子序列有序
            Merge(A,low,mid,high,temp);//将两个有序子数组合并操作
        }
    }
    public static void Merge(int[] A,int low,int mid,int high,int[] temp)//合并操作
    {
        int i=low;//左序列指针
        int j=mid+1;//右序列指针
        int t=0;//临时数组指针
        while(i<=mid&&j<=high)//把较小的数先移到新数组中
        {
            if (A[i] <= A[j])
                temp[t++] = A[i++];
            else
                temp[t++] = A[j++];
        }
        while(i<=mid)//把左边剩余的数移入temp数组
            temp[t++]=A[i++];
        while(j<=high)//把右边剩余的数移入temp数组
            temp[t++]=A[j++];
        t=0;
        while(low<=high)//把temp数组中的数覆盖到A中
            A[low++]=temp[t++];
    }
    public static void MergeSort2(int[] A)//归并排序,非递归
    {
        int[] temp=new int[A.length];
        for(int i=1;i<A.length;i=i*2)//i表示每一分区的长度
        {
            for (int j = 0; j + i <= A.length; j += i * 2)
            {
                int low=j,mid=j+i-1,high=j+2*i-1;
                if(high>A.length-1) high=A.length-1;//整个待排序数组为奇数的情况
                Merge(A,low,mid,high,temp);
            }
        }
    }

    public static void hashSort(int[] nums)
    {
        int[] array=find(nums);
        int min=array[0];
        int max=array[1];
        hashSort(nums,min,max);
    }
    public  static int[] find(int[] nums)
    {
        int min=nums[0];
        int max=nums[0];
        for(int i=1;i<nums.length;i++)
        {
            if(nums[i]<min)
                min=nums[i];
            if(nums[i]>max)
                max=nums[i];
        }
        int[]res=new int[2];
        res[0]=min;
        res[1]=max;
        return res;
    }
    public static  void hashSort(int[] nums,int min,int max)
    {
        int[] temp=new int[max+1-min];
        for(int i=0;i<nums.length;i++)
        {
            temp[nums[i]-min]++;
        }
        for(int i=0,count=0;i<temp.length;i++)
        {
            if(temp[i]!=0)
            {
                int t=temp[i];
                while(t!=0)
                {
                    nums[count++]=i+min;
                    t--;
                }
            }
        }
    }
}

