package randomthreadpool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Callabletest {

    public void callable() {
        final ExecutorService service;

        service = Executors.newFixedThreadPool(20);
        Set<Future<OutputMessage>> set = new HashSet<>();
        ArrayList<OutputMessage> outputs = new ArrayList<>();
        outputs.ensureCapacity(200);

        for (int i = 0; i < 200; i++) {
            outputs.add(new OutputMessage());
        }

        for (int i = 0; i < 200; i++) {
            InputMessage iMess = new InputMessage(i, i + 2);
            Callable<OutputMessage> callable = new RandomNumberSeeding(iMess);
            Future<OutputMessage> future = service.submit(callable);
            set.add(future);
        }

        set.stream().forEach((out) -> {
            try {
                int thread = out.get().getThread();
                double[] list = out.get().getList();
                outputs.set(thread, out.get());


            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(Callabletest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        service.shutdownNow();

        for (OutputMessage o : outputs) {

            for (double d : o.getList()) {
                  //  System.out.println("thread " + thread + " double: " + d);

                System.out.println("Thread: " + o.getThread() + "; Number: " + d);
            }
        }

    }
}
