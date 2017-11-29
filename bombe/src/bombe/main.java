
package bombe;

public class main {
    public static void main(String[] args) {
        Bombe t = new Bombe();
        t.addTempListener(new TempListener(){
            @Override
            public void temp(TempEvent e) {
                System.out.println("tick");
            }
        });
        t.start();
    }
}