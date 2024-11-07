import java.util.Scanner; 
public class SJF{ 
    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
        System.out.print("Enter number of processes: "); 
        int n = sc.nextInt();        
        int[] pid = new int[n]; 
        int[] burst = new int[n]; 
        int[] arrival = new int[n]; 
        int[] wait = new int[n]; 
        int[] turn = new int[n]; 
        int[] start = new int[n]; 
        int[] finish = new int[n]; 
        float totalWait = 0, totalTurn = 0; 
         
        for (int i = 0; i < n; i++) { 
            pid[i] = i + 1; 
            System.out.print("Enter arrival time for process " + (i + 1) + ": "); 
            arrival[i] = sc.nextInt(); 
            System.out.print("Enter burst time for process " + (i + 1) + ": "); 
            burst[i] = sc.nextInt(); 
        } 
        boolean[] done = new boolean[n]; 
        int currentTime = 0, completed = 0; 
        while (completed < n) { 
            int idx = -1, minBurst = Integer.MAX_VALUE; 
            for (int i = 0; i < n; i++) { 
                if (arrival[i] <= currentTime && !done[i] && burst[i] < minBurst) { 
                    minBurst = burst[i]; 
                    idx = i; 
                } 
            } 
            if (idx == -1) { 
                currentTime++; 
            } else { 
                start[idx] = currentTime; 
                finish[idx] = start[idx] + burst[idx]; 
                wait[idx] = start[idx] - arrival[idx]; 
                turn[idx] = finish[idx] - arrival[idx]; 
                 
                totalWait += wait[idx]; 
                totalTurn += turn[idx]; 
                 
                currentTime += burst[idx]; 
                done[idx] = true; 
                completed++; 
            } 
        } 
        System.out.println("Process\tArrival Time\tBurst Time\tCompletion Time\tWaiting Time\tTurnaround Time"); 
        for (int i = 0; i < n; i++) { 
            System.out.println("P" + pid[i] + "\t\t" + arrival[i] + "\t\t" + burst[i] + "\t\t" + finish[i] + "\t\t" + wait[i] + "\t\t" + turn[i]); 
        } 
        float avgWait = totalWait / n; 
        float avgTurn = totalTurn / n; 
        System.out.printf("\nAverage Waiting Time: %.2f", avgWait); 
        System.out.printf("\nAverage Turnaround Time: %.2f", avgTurn); 
        System.out.print("\n\nGantt Chart: |"); 
        for (int i = 0; i < n; i++) { 
            if (done[i]) { 
                System.out.print(" P" + pid[i] + " |"); 
            } 
        } 
        System.out.print("\n0"); 
        for (int i = 0; i < n; i++) { 
            System.out.print("   " + finish[i]); 
        } 
    } 
} 