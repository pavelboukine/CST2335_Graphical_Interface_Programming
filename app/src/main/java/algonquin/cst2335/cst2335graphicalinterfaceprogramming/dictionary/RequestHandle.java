package algonquin.cst2335.cst2335graphicalinterfaceprogramming.dictionary;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * The RequestHandle class provides a singleton wrapper for managing a Volley RequestQueue.
 * It ensures that a single instance of RequestQueue is used throughout the application.
 */
public class RequestHandle {

    private static RequestHandle inst = null;

    private final RequestQueue queue;

    /**
     * Private constructor to create a new RequestHandle with a specified application context.
     *
     * @param context The application context used to create the Volley RequestQueue.
     */
    private RequestHandle(Context context) {
        this.queue = Volley.newRequestQueue(context);
    }

    /**
     * Sets the application context for the RequestHandle, creating a new instance if needed.
     *
     * @param context The application context to be set.
     */
    public static void setContext(Context context) {
        RequestHandle.inst = new RequestHandle(context);
    }


    /**
     * Retrieves the managed Volley RequestQueue instance.
     *
     * @return The managed Volley RequestQueue instance.
     * @throws RuntimeException Thrown when the context is not set. Call setContext() before using this method.
     */
    public static RequestQueue getQueue()
        throws RuntimeException
    {
        if (RequestHandle.inst == null)
            throw new RuntimeException("Context not set. Please call RequestHandle.setContext().");
        return RequestHandle.inst.queue;
    }
}
