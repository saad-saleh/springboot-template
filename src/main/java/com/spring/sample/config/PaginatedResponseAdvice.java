package com.spring.sample.config;


import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.util.UriComponentsBuilder;

@RestControllerAdvice
@Order(1)
public class PaginatedResponseAdvice<T> implements ResponseBodyAdvice<T> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return PageImpl.class.isAssignableFrom(returnType.getParameterType());

    }

    @Override
    public T beforeBodyWrite(T value, MethodParameter returnType, MediaType selectedContentType,
                             Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                             ServerHttpResponse response) {

        if (!(value instanceof PageImpl<?>)) {
            return value;
        }
        PageImpl<?> page = (PageImpl<?>) value;
        HttpHeaders headers = response.getHeaders();
        headers.set("Access-Control-Expose-Headers", "Link,Page-Number,Page-Size,Total-Elements,Total-Pages");

        headers.set("X-Total-Count", String.valueOf(page.getTotalElements()));
        headers.set("X-Result-Count", String.valueOf(page.getNumberOfElements()));

        String links = links(page, request);
        if (links.length() > 0)
            headers.set("Link", links);
        return value;
    }

    private String links(PageImpl<?> page, ServerHttpRequest request) {
        StringBuffer links = new StringBuffer();
        UriComponentsBuilder link;
        UriComponentsBuilder builder = UriComponentsBuilder.fromUri(request.getURI());
        if (request.getURI().getHost() == "localhost") {
            builder.port(request.getURI().getPort());
        }

        if (!page.isFirst()) {
            link = replacePageAndSize(page.getPageable().first(), builder);
            links.append("<" + link.toUriString() + ">; rel=\"first\"");
        }

        if (page.hasPrevious()) {
            link = replacePageAndSize(page.getPageable().previousOrFirst(), builder);
            links.append("<" + link.toUriString() + ">; rel=\"prev\"");
        }

        if (page.hasNext()) {
            link = replacePageAndSize(page.getPageable().next(), builder);
            links.append("<" + link.toUriString() + ">; rel=\"next\"");
        }

        if (!page.isLast()) {
            UriComponentsBuilder last = builder.cloneBuilder();
            last.replaceQueryParam("page", page.getTotalPages());
            last.replaceQueryParam("size", page.getSize());

            links.append("<" + last.toUriString() + ">; rel=\"last\"");
        }

        return links.toString();
    }

    private UriComponentsBuilder replacePageAndSize(Pageable page, UriComponentsBuilder oldBuilder) {
        UriComponentsBuilder builder = oldBuilder.cloneBuilder();

        int pageNumber = page.getPageNumber();

        builder.replaceQueryParam("page", pageNumber);
        builder.replaceQueryParam("size", page.getPageSize());

        return builder;
    }

}

