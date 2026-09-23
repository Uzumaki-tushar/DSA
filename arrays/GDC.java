import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

class GDC{
    public static void main(String[] args){
        System.out.println("hi hello");
        Scanner sc= new Scanner(System.in);

        // int first=sc.nextInt();
        // int second=sc.nextInt();

        // find gdc

        // System.out.println(findGDC(first,second));

        // find all prime number
        int n= sc.nextInt();

        // System.out.println(findAllPrime(n));

        // find sum of divisor
        System.out.println(sumOfDivisor(n));
    }


    // find gdc
    public static int findGDC(int first,int second){
        if(first==0) return second;
        if(second==0) return first;

        if(first>second) return findGDC(first%second, second);
        else return findGDC(second%first, first);
    }

    // find all prime numbers in range
    public static int findAllPrime(int n){
        int[] arr= new int[n+1];
        Arrays.fill(arr,1);

        for(int i=2;i<=n;i++){
            for(int j=i*i;j<=n;j+=i){
                arr[j]=0;
            }
        }

        int cnt=0;
        for(int i=2;i<=n;i++){
            if(arr[i]==1) cnt++;
        }

        return cnt;

    }

    // find sum of all divisor
    public static int sumOfDivisor(int n){
        int sum=0;
        for(int i=1;i<=n;i++){
            sum+=(i*(n/i));
        }
        return sum;
    }
}