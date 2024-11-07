import java.util.Scanner; 
public class PriorityScheduling { 
    static int jtime = 0; 
    static int counter; 
    static int n; 
    static int[] at = new int[20], bt = new int[20], bt_copy = new int[20], ct = new int[20], pt = new int[20], sts = new int[20]; 
    static int[] tat = new int[20], wt = new int[20]; 
    static String ganttChart = ""; 
    static String ganttTimes = "0 "; 
    static void process_Ps() { 
        int index = -1; 
        int highestPriority = Integer.MAX_VALUE; 
        for (int i = 0; i < n; i++) { 
            if (at[i] <= jtime && sts[i] != 1) { 
                if (pt[i] < highestPriority) { 
                    highestPriority = pt[i]; 
                    index = i; 
                } 
            } 
        } 
        if (index != -1) { 
            if (bt[index] > 0) { 
                ganttChart += "| P" + (index + 1) + " "; 
                jtime++; 
                ganttTimes += jtime + " "; 
                bt[index]--; 
            } 
            if (bt[index] == 0) { 
                ct[index] = jtime; 
                sts[index] = 1; 
                counter--; 
            } 
        } else { 
            jtime++; 
            ganttTimes += jtime + " "; 
        } 
    } 
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
 
        System.out.print("Enter the number of processes: "); 
        n = sc.nextInt(); 
        counter = n; 
        for (int i = 0; i < n; i++) { 
            System.out.println("Process " + (i + 1) + ":"); 
            System.out.print("Arrival time: "); 
            at[i] = sc.nextInt(); 
            System.out.print("Burst time: "); 
            bt[i] = sc.nextInt(); 
            bt_copy[i] = bt[i]; 
            System.out.print("Priority: "); 
            pt[i] = sc.nextInt(); 
            sts[i] = 0; 
            ct[i] = 0; 
        } 
        System.out.println("\nGiven details:"); 
        System.out.println("PID     AT      BT      PT"); 
        for (int i = 0; i < n; i++) { 
            System.out.printf("P%-7d%-7d%-7d%-7d\n", (i + 1), at[i], bt_copy[i], pt[i]); 
        } 
        while (counter != 0) { 
            process_Ps(); 
        } 
        double totalTAT = 0, totalWT = 0; 
        for (int i = 0; i < n; i++) { 
            tat[i] = ct[i] - at[i]; 
            wt[i] = tat[i] - bt_copy[i]; 
            totalTAT += tat[i]; 
            totalWT += wt[i]; 
        } 
        System.out.println("\nResults:"); 
        System.out.println("PID     AT      BT      PT      CT      TAT     WT"); 
        for (int i = 0; i < n; i++) { 
            System.out.printf("P%-7d%-7d%-7d%-7d%-7d%-7d%-7d\n", (i + 1), at[i], bt_copy[i], pt[i], ct[i], tat[i], wt[i]); 
        } 
        System.out.println("\nGantt Chart:"); 
        System.out.println(ganttChart + "|"); 
        System.out.println(ganttTimes); 
        System.out.printf("\nAverage Turnaround Time: %.2f\n", (totalTAT / n)); 
        System.out.printf("Average Waiting Time: %.2f\n", (totalWT / n)); 
        sc.close(); 
    } 
} 