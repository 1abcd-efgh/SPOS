import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RR {
    public static void main(String args[])throws IOException{
        DataInputStream in = new DataInputStream(System.in);
        
        int i,j,k,q,sum=0;
        System.out.println("Enter Number of Process:");
        int n = Integer.parseInt(in.readLine());
        int bt[]=new int[n];
        int wt[]=new int[n];
        int tat[]=new int[n];
        int a[]=new int[n];
        int rtime[] = new int[n];

        for(i=0;i<n;i++){
            System.out.print("Enter Brust Time For Process "+(i+1)+" :");
            bt[i]=Integer.parseInt(in.readLine());
            rtime[i] = bt[i];
            wt[i] = 0;
        }
        System.out.println("Enter Time Qyantum");
        q=Integer.parseInt(in.readLine());
        for(i=0;i<n;i++){
            a[i]=bt[i];
        }
        for(i=0;i<n;i++){
            wt[i]=0;
        }
        do{
            for(i=0;i<n;i++){
                if(bt[i]>q){
                    bt[i]-=q;
                    for(j=0;j<n;j++){
                        if((j!=i)&&(bt[j]!=0)){
                            wt[j]+=q;
                        }
                    }
                }
                else{
                    for(j=0;j<n;j++){
                        if((j!=i)&&(bt[j]!=0)){
                            wt[j]+=bt[i];
                        }
                    }
                    bt[i]=0;
                }
            }
            sum=0;
            for(k=0;k<n;k++){
                sum=sum+bt[k];
            }
        }while(sum!=0);
        for(i=0;i<n;i++){
            tat[i]=wt[i]+a[i];
        }
        System.out.println("---------------------------------------------");
        System.out.println("Process\t\tBT\tWT\tTAT");
        System.out.println("---------------------------------------------");
        for(i=0;i<n;i++){
            System.out.println("Process"+(i+1)+"\t"+a[i]+"\t"+wt[i]+"\t"+tat[i]);
        }
        float avg_wt=0, avg_tat=0;
        for(j=0;j<n;j++){
            avg_wt+=wt[j];
            avg_tat+=tat[j];
        }
        System.out.println("Average Waiting Time : "+(avg_wt/n)+"\nAverage Turnaround Time : "+(avg_tat/n));

        List<String> ganttChart = new ArrayList<>();
        List<Integer> ganttTime = new ArrayList<>();
        int rp = n;
        i = 0;
        int time = 0;
        ganttTime.add(time);

        while (rp != 0) {
            if (rtime[i] > 0) {
                if (rtime[i] > q) {
                    time += q;
                    rtime[i] -= q;
                } else {
                    time += rtime[i];
                    rtime[i] = 0;
                    rp--;
                }

                ganttChart.add("P[" + (i + 1) + "]");
                ganttTime.add(time);
            }
            i++;
            if (i == n) {
                i = 0;
            }
        }

        System.out.println("\n\nGantt Chart:");
        for (String process : ganttChart) {
            System.out.print("| " + process + " ");
        }
        System.out.println("|");

        for (Integer t : ganttTime) {
            System.out.print(t + "      ");
        }
    }
    
}
