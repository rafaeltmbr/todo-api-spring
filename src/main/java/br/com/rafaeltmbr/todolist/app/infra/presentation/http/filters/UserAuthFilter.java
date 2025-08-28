package br.com.rafaeltmbr.todolist.app.infra.presentation.http.filters;

import br.com.rafaeltmbr.todolist.app.infra.presentation.http.controllers.ExceptionController;
import br.com.rafaeltmbr.todolist.user.core.entities.User;
import br.com.rafaeltmbr.todolist.user.core.entities.UserAuthenticationToken;
import br.com.rafaeltmbr.todolist.user.core.exceptions.UserException;
import br.com.rafaeltmbr.todolist.user.infra.data.repositories.IUserRepositoryJpa;
import br.com.rafaeltmbr.todolist.user.infra.di.UserContainer;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class UserAuthFilter extends OncePerRequestFilter {
    @Autowired
    private IUserRepositoryJpa userRepositoryJpaInterface;

    private static void sendErrorResponse(HttpStatus status, ExceptionController.ErrorResponseBody errorResponseBody, HttpServletResponse response) throws IOException {
        response.setStatus(status.value());
        response.setHeader("Content-Type", "application/json");
        response.getWriter().write(errorResponseBody.toJson());
        response.getWriter().flush();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.startsWith("/user")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null) {
            var body = new ExceptionController.ErrorResponseBody(
                    "missing_authorization_header",
                    "Missing authorization header."
            );
            sendErrorResponse(HttpStatus.BAD_REQUEST, body, response);
            return;
        }

        UserAuthenticationToken token;
        try {
            token = new UserAuthenticationToken(authorizationHeader.split(" ")[1]);
        } catch (UserException exception) {
            var body = new ExceptionController.ErrorResponseBody(
                    "missing_authorization_token",
                    "Missing authorization token."
            );
            sendErrorResponse(HttpStatus.BAD_REQUEST, body, response);
            return;
        }

        try {
            var userContainer = UserContainer.getInstance(userRepositoryJpaInterface);
            User user = userContainer.useCases.authenticateUser().execute(token);
            request.setAttribute("user", user);
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setHeader("Content-Type", "application/json");
            var body = new ExceptionController.ErrorResponseBody("authentication_failed", exception.getMessage());
            response.getWriter().write(body.toJson());
            response.getWriter().flush();
        }
    }
}
