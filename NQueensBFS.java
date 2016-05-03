package nvezirbfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class NVezirBFS {

    int N, test = 0;
    ArrayList<Integer[]> roots, children;
    ArrayList totals, differences;
    Integer[] dizi;
    
    public NVezirBFS(int N) {
        this.N = N;
        roots = new ArrayList<>();
        children = new ArrayList<>();
        totals = new ArrayList();
        differences = new ArrayList();
        dizi = new Integer[N];
        for (int i = 0; i < N; i++) {
            dizi[i] = i + 1;
        }
        roots.add(dizi);
    }
    
    private int createChild(){
        for (int pivot = 0; pivot < N - 1 ; pivot++) {
            for (int root = 0; root < roots.size(); root++) {
                for (int i = pivot; i < N ; i++){
                    Integer[] geciciRoot = roots.get(root).clone();
                    int gecici = geciciRoot[i];
                    geciciRoot[i] = geciciRoot[pivot];
                    geciciRoot[pivot] = gecici;
                    children.add(geciciRoot);
                    if(kontrol(geciciRoot)){
                        System.out.println("Solution : " + Arrays.toString(geciciRoot));
                        return test;
                    }
                }
            }
            roots.clear();
            roots = (ArrayList<Integer[]>) children.clone();
            children.clear();
        }
        return 0;
    }
    
    private boolean kontrol(Integer[] tahta){
        test++;
        
        totals.clear();
        differences.clear();
        
        for (int i = 0; i < tahta.length; i++) {
            totals.add(i + tahta[i]);
            differences.add(i - tahta[i]);
        }
        
        for (int i = 0; i < tahta.length; i++) {
            for (int j = 0; j < tahta.length; j++) {
                if (i != j) {
                    if (totals.get(i) == totals.get(j) || differences.get(i) == differences.get(j)) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Queens : ");
        NVezirBFS nVezirBFS = new NVezirBFS(input.nextInt());
        System.out.println(nVezirBFS.createChild() + ". node for first solution.");
    }
    
}
