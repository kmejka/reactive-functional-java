package pl.kmejka.assertions;

import javax.ws.rs.core.Response;

import static org.fest.assertions.api.Assertions.*;

/**
 * @author kmejka
 */
public class RestAssertionSweets {

    public static void responseCode403(Response response ) {
        assertThat(response.getStatus()).isEqualTo(Response.Status.FORBIDDEN.getStatusCode());
    }

    public static void responseCode401(Response response ) {
        assertThat(response.getStatus()).isEqualTo(Response.Status.UNAUTHORIZED.getStatusCode());
    }

    public static void responseCode500(Response response ) {
        assertThat(response.getStatus()).isEqualTo(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
    }

    public static void responseCode400(Response response) {
        assertThat(response.getStatus()).isEqualTo(Response.Status.BAD_REQUEST.getStatusCode());
    }

    public static void responseCode415(Response response) {
        assertThat(response.getStatus()).isEqualTo(Response.Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode());
    }

    public static void responseCode404(Response response) {
        assertThat(response.getStatus()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
    }

    public static void responseCode200(Response response) {
        assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
    }

//    public static void responseContainsMessage(final Response response, final String messageToCheck) {
//        assertThat(getResponseContentAsString(response).replace("\"", "").replace("\\", "")).contains(messageToCheck.replace("\"", "").replace("\\", ""));
//    }
//
//    public static String getResponseContentAsString(final Response response) {
//        return response.getEntity();
//    }
}
