package properties;

import lombok.Generated;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "database.config.properties")
public class DatabaseProperties {

    private String username;
    private String secret;
    private String driverClassName;
    private String url;
    private String poolName;
    private int minPoolSize;
    private int maxPoolSize;
    private long maxLifetime;
    private long validationTimeout;
    private String schema;
    private boolean showSql;
    private boolean formatSql;
    private String scanPackages;

    public DatabaseProperties() {
    }

    @Generated
    public String getUsername() {
        return username;
    }
    @Generated
    public void setUsername(String username) {
        this.username = username;
    }
    @Generated
    public String getSecret() {
        return secret;
    }
    @Generated
    public void setSecret(String secret) {
        this.secret = secret;
    }
    @Generated
    public String getDriverClassName() {
        return driverClassName;
    }
    @Generated
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
    @Generated
    public String getUrl() {
        return url;
    }
    @Generated
    public void setUrl(String url) {
        this.url = url;
    }
    @Generated
    public String getPoolName() {
        return poolName;
    }
    @Generated
    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }
    @Generated
    public int getMinPoolSize() {
        return minPoolSize;
    }
    @Generated
    public void setMinPoolSize(int minPoolSize) {
        this.minPoolSize = minPoolSize;
    }
    @Generated
    public int getMaxPoolSize() {
        return maxPoolSize;
    }
    @Generated
    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }
    @Generated
    public long getMaxLifetime() {
        return maxLifetime;
    }
    @Generated
    public void setMaxLifetime(long maxLifetime) {
        this.maxLifetime = maxLifetime;
    }
    @Generated
    public long getValidationTimeout() {
        return validationTimeout;
    }
    @Generated
    public void setValidationTimeout(long validationTimeout) {
        this.validationTimeout = validationTimeout;
    }
    @Generated
    public String getSchema() {
        return schema;
    }
    @Generated
    public void setSchema(String schema) {
        this.schema = schema;
    }
    @Generated
    public boolean isShowSql() {
        return showSql;
    }
    @Generated
    public void setShowSql(boolean showSql) {
        this.showSql = showSql;
    }
    @Generated
    public boolean isFormatSql() {
        return formatSql;
    }
    @Generated
    public void setFormatSql(boolean formatSql) {
        this.formatSql = formatSql;
    }
    @Generated
    public String getScanPackages() {
        return scanPackages;
    }
    @Generated
    public void setScanPackages(String scanPackages) {
        this.scanPackages = scanPackages;
    }
}
