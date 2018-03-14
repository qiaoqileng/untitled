package lifting.bean.base;

public class CommonResult<T> {

    private boolean error;
    private T results;
    private String errorMessage;

    public CommonResult(boolean error, T results, String errorMessage) {
        this.error = error;
        this.results = results;
        this.errorMessage = errorMessage;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
