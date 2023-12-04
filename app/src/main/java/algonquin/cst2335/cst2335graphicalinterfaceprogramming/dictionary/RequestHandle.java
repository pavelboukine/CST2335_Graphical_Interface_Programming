package algonquin.cst2335.cst2335graphicalinterfaceprogramming.dictionary;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestHandle {

    private static RequestHandle inst = null;

    private final RequestQueue queue;

    private RequestHandle(Context context) {
        this.queue = Volley.newRequestQueue(context);
    }

    public static void setContext(Context context) {
        RequestHandle.inst = new RequestHandle(context);
    }

    /**
     * @return managed Volley RequestQueue.
     * @throws RuntimeException when Context is not set.
     */
    public static RequestQueue getQueue()
        throws RuntimeException
    {
        if (RequestHandle.inst == null)
            throw new RuntimeException("Context not set. Please call RequestHandle.setContext().");
        return RequestHandle.inst.queue;
    }
}
