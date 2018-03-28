package com.notronix.mws.service;

import com.notronix.mws.AmazonMarketplace;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.util.*;

import static com.notronix.albacore.ContainerUtils.thereAreNo;
import static com.notronix.mws.service.StandardAmazonCredentials.EMPTY_CREDENTIALS;
import static java.util.stream.Collectors.groupingBy;

public final class StandardAmazonConfiguration implements AmazonConfiguration
{
    private static final Logger log = LogManager.getLogger(StandardAmazonConfiguration.class);

    private static final String PROPERTIES_FILE = "amws.properties";
    private static final String ROOT_CREDENTIAL = "ROOT";
    private static final String MERCHANT_ID = ".merchantId";
    private static final String ACCESS_KEY = ".accessKey";
    private static final String SECRET_KEY = ".secretKey";
    private static final String APP_NAME = ".applicationName";
    private static final String APP_VERSION = ".applicationVersion";

    private Map<AmazonMarketplace, AmazonCredentials> credentialsForMarketplaces = new HashMap<>();

    @Override
    public AmazonConfiguration configure() {
        Properties properties = new Properties();

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);

        if (inputStream == null) {
            log.debug("No " + PROPERTIES_FILE + " file found on classpath.");
            return this;
        }

        try {
            properties.load(inputStream);

            Map<String, List<String>> propertiesByMarketplace = mapPropertiesByMarketplace(properties);

            String defaultAppName = getProperty(properties, propertiesByMarketplace.get(ROOT_CREDENTIAL), APP_NAME, "");
            String defaultAppVersion = getProperty(properties, propertiesByMarketplace.get(ROOT_CREDENTIAL), APP_VERSION, "");

            Arrays.stream(AmazonMarketplace.values()).forEach(marketplace ->
                    buildCredentialsForMarketplace(properties, propertiesByMarketplace, marketplace, defaultAppName,
                            defaultAppVersion));
        }
        catch (Exception ex) {
            log.error("Error loading properties from " + PROPERTIES_FILE, ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (Exception ex) {
                log.debug("Unable to close input stream.", ex);
            }
        }

        return this;
    }

    @Override
    public Map<AmazonMarketplace, AmazonCredentials> getCredentialsMap() {
        return credentialsForMarketplaces;
    }

    private void buildCredentialsForMarketplace(Properties properties,
                                                Map<String, List<String>> propertiesByMarketplace,
                                                AmazonMarketplace marketplace,
                                                String defaultAppName,
                                                String defaultAppVersion) {
        StandardAmazonCredentials credentials;
        List<String> propertyNames = propertiesByMarketplace.get(marketplace.name());

        if (thereAreNo(propertyNames)) {
            credentials = EMPTY_CREDENTIALS;
        }
        else {
            credentials = new StandardAmazonCredentials(marketplace,
                    getProperty(properties, propertyNames, MERCHANT_ID, ""),
                    getProperty(properties, propertyNames, ACCESS_KEY, ""),
                    getProperty(properties, propertyNames, SECRET_KEY, ""),
                    getProperty(properties, propertyNames, APP_NAME, defaultAppName),
                    getProperty(properties, propertyNames, APP_VERSION, defaultAppVersion));
        }

        credentialsForMarketplaces.put(marketplace, credentials);
    }

    private String getProperty(Properties properties, List<String> propertyNames, String suffix, String defaultValue) {
        if (properties == null) {
            return defaultValue;
        }

        String property = properties.getProperty(propertyNames.stream()
                .filter(name -> name != null && name.endsWith(suffix))
                .findAny().orElse(""));

        return property == null ? defaultValue : property;
    }

    private Map<String, List<String>> mapPropertiesByMarketplace(Properties properties) {
        return properties == null ? Collections.emptyMap() : properties.stringPropertyNames().stream()
                .collect(groupingBy(propertyName -> {
                    try {
                        return AmazonMarketplace.valueOf(propertyName.substring(0, 2)).name();
                    }
                    catch (Exception ex) {
                        return ROOT_CREDENTIAL;
                    }
                }));
    }
}
