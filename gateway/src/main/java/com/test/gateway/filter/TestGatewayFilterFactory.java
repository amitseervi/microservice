package com.test.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class TestGatewayFilterFactory extends AbstractGatewayFilterFactory<TestGatewayFilterFactory.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        return new TestHeaderFilter(config);
    }

    @Override
    public String name() {
        return "test-filter";
    }

    public static class Config {
        //Pass filter if headerName is present or fail in request header
        private final String headerName;
        private final String errorRedirectPath;

        public Config(String headerName, String errorRedirectPath) {
            this.headerName = headerName;
            this.errorRedirectPath = errorRedirectPath;
        }

        public String getHeaderName() {
            return headerName;
        }

        public String getErrorRedirectPath() {
            return errorRedirectPath;
        }
    }
}
