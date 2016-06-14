'use strict';

/**
 * @ngdoc function
 * @name dockerUiApp.controller:NewcreditrequestCtrl
 * @description
 * # NewcreditrequestCtrl
 * Controller of the dockerUiApp
 */
angular.module('dockerUiApp')
  .controller('NewcreditrequestCtrl', ['$rootScope', '$scope', 'CreateCreditRequest', function ($rootScope, $scope, creditCreditRequest) {

    $rootScope.alerts = $rootScope.alerts || [];

    $rootScope.alerts = $rootScope.alerts || [];
    $rootScope.closeAlert = function(alert)
    {
      $rootScope.alerts.splice($rootScope.alerts.indexOf(alert), 1);
    };

    $scope.creditRequest = {};

    $scope.sendCreditRequest = function () {
      creditCreditRequest.sendCreditRequest($scope.creditRequest).
        success(function () {
          // this callback will be called asynchronously
          // when the response is available
          $rootScope.alerts.push({message: 'Credit request ontvangen', type: 'success'});
        }).
        error(function (data, status) {
          // called asynchronously if an error occurs
          // or server returns response with an error status.
          var message = 'Status: ' + status + ': ' + angular.toJson(data, true);
          $rootScope.alerts.push({message: message, type: 'danger'});
        });
    };
  }]);
