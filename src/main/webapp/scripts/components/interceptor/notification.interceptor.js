 'use strict';

angular.module('enigme30App')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-enigme30App-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-enigme30App-params')});
                }
                return response;
            }
        };
    });
