package com.tempmail.mailutil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tester3 on 19.10.2016.
 */
public class HttpRequestTempMail {

    private int timeout = 3500;
    private URL url;
    private boolean doOutPut = true, doInPut = true;
    private String output = "", postData = "", requestMethod, cookie, referer,
            useragent = "Mozilla/5.0 (Windows NT 6.4; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2225.0 Safari/537.36";
    private Proxy proxy = null;
    private final HashMap<String, List<String>> HEADER_MAP = new HashMap<String, List<String>>();
    private final HashMap<String, String> REQUEST_PROPERTY_MAP = new HashMap<String, String>();
    private int responseCode = -1;

    /**
     * Instantiates a new http request.
     *
     * @param url           the url
     * @param requestMethod the request method
     * @param proxy         the proxy
     */
    public HttpRequestTempMail(final URL url, final String requestMethod, final Proxy proxy) {
        this.url = url;
        this.setRequestMethod(requestMethod);
        this.setProxy(proxy);
    }

    /**
     * Instantiates a new http request.
     *
     * @param url           the url
     * @param requestMethod the request method
     * @throws MalformedURLException the malformed url exception
     */
    public HttpRequestTempMail(final String url, final String requestMethod) throws MalformedURLException {
        this(new URL(url), requestMethod, null);
    }

    /**
     * Instantiates a new http request.
     *
     * @param url the url
     * @throws MalformedURLException the malformed url exception
     */
    public HttpRequestTempMail(final String url) throws MalformedURLException {
        this(new URL(url), "GET", null);
    }

    /**
     * Adds the request property.
     *
     * @param key   the key
     * @param value the value
     * @return the string
     */
    public String addRequestProperty(final String key, final String value) {
        return REQUEST_PROPERTY_MAP.put(key, value);
    }

    /**
     * Removes the request property.
     *
     * @param key the key
     * @return the string
     */
    public String removeRequestProperty(final String key) {
        return REQUEST_PROPERTY_MAP.remove(key);
    }

    /**
     * Gets the request property map.
     *
     * @return the request property map
     */
    public HashMap<String, String> getRequestPropertyMap() {
        return REQUEST_PROPERTY_MAP;
    }

    public void execute() throws IOException {
        final HttpURLConnection httpConnection = (HttpURLConnection) (getProxy() != null
                ? url.openConnection(getProxy()) : url.openConnection());

        httpConnection.setDoOutput(isDoOutPut());
        httpConnection.setDoInput(isDoInPut());
        httpConnection.setConnectTimeout(getTimeout());

        if (getRequestMethod() != null) {
            httpConnection.setRequestMethod(getRequestMethod());
        }
        if (getUseragent() != null) {
            httpConnection.setRequestProperty("User-Agent", getUseragent());
        }
        if (cookie != null) {
            httpConnection.setRequestProperty("Cookie", cookie);
        }
        if (referer != null) {
            httpConnection.setRequestProperty("Referer", referer);
        }
        // setting the rest of the property maps.
        REQUEST_PROPERTY_MAP.entrySet().forEach(v -> {
            httpConnection.setRequestProperty(v.getKey(), v.getKey());
        });

        if (getPostData() != null && getPostData().length() > 0) {
            final OutputStream outputStream = httpConnection.getOutputStream();
            if (outputStream != null) {
                try (OutputStreamWriter osw = new OutputStreamWriter(outputStream)) {
                    if (osw != null) {
                        osw.write(getPostData());
                        osw.flush();
                    }
                }
            }
        }

        final InputStream inputStream = httpConnection.getInputStream();
        if (inputStream != null) {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            if (bufferedReader != null) {
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    output += line;
                }
            }
        }
        httpConnection.getHeaderFields().entrySet().forEach(e -> {
            HEADER_MAP.put(e.getKey(), e.getValue());
        });
        responseCode = httpConnection.getResponseCode();
        httpConnection.disconnect();

    }

    /**
     * Gets the cookie.
     *
     * @return the cookie
     */
    public String getCookie() {
        return cookie;
    }

    /**
     * Sets the cookie.
     *
     * @param cookie the new cookie
     */
    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    /**
     * Gets the request method.
     *
     * @return the request method
     */
    public String getRequestMethod() {
        return requestMethod;
    }

    /**
     * Sets the request method.
     *
     * @param requestMethod the new request method
     */
    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    /**
     * Gets the proxy.
     *
     * @return the proxy
     */
    public Proxy getProxy() {
        return proxy;
    }

    /**
     * Sets the proxy.
     *
     * @param proxy the new proxy
     */
    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    /**
     * Gets the referer.
     *
     * @return the referer
     */
    public String getReferer() {
        return referer;
    }

    /**
     * Sets the referer.
     *
     * @param referer the new referer
     */
    public void setReferer(String referer) {
        this.referer = referer;
    }

    /**
     * Gets the useragent.
     *
     * @return the useragent
     */
    public String getUseragent() {
        return useragent;
    }

    /**
     * Sets the useragent.
     *
     * @param useragent the new useragent
     */
    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    /**
     * Checks if is do out put.
     *
     * @return true, if is do out put
     */
    public boolean isDoOutPut() {
        return doOutPut;
    }

    /**
     * Sets the do out put.
     *
     * @param doOutPut the new do out put
     */
    public void setDoOutPut(boolean doOutPut) {
        this.doOutPut = doOutPut;
    }

    /**
     * Checks if is do in put.
     *
     * @return true, if is do in put
     */
    public boolean isDoInPut() {
        return doInPut;
    }

    /**
     * Sets the do in put.
     *
     * @param doInPut the new do in put
     */
    public void setDoInPut(boolean doInPut) {
        this.doInPut = doInPut;
    }

    /**
     * Gets the output.
     *
     * @return the output
     */
    public String getOutput() {
        return output;
    }

    /**
     * Gets the post data.
     *
     * @return the post data
     */
    public String getPostData() {
        return postData;
    }

    /**
     * Sets the post data.
     *
     * @param postData the new post data
     */
    public void setPostData(String postData) {
        this.postData = postData;
    }

    public void addToPostData(final HttpPostParameter... parameters) {
        if (parameters != null) {
            for (HttpPostParameter v : parameters) {
                if (v != null) {
                    postData += v.getField() + "=" + v.getValue() + "&";
                }
            }
        }
    }


    /**
     * Gets the timeout.
     *
     * @return the timeout
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * Sets the timeout for the connection.
     *
     * @param timeout the new timeout
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * Gets the connection headers.
     *
     * @return the connection headers
     */
    public HashMap<String, List<String>> getConnectionHeaders() {
        return HEADER_MAP;
    }

    /**
     * Gets the response code. Returns -1 when post not executed.
     *
     * @return the response code
     */
    public int getResponseCode() {
        return responseCode;
    }
}
