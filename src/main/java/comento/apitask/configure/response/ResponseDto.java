package comento.apitask.configure.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Builder
@AllArgsConstructor
@Getter
public class ResponseDto<T> {
    private int status;
    private String Message;
    private T data;

    public ResponseDto(final int status, final T data) {
        this.status = status;
        this.Message = null;
        this.data = data;
    }

    public static<T> ResponseDto<T> response(final int status, final T data) {
        return ResponseDto.<T>builder()
                .status(status)
                .data(data)
                .build();
    }

    public static<T> ResponseDto<T> response(final int status, final String message) {
        return ResponseDto.<T>builder()
                .status(status)
                .Message(message)
                .build();
    }
}
