'use strict';

/**
 * @ngdoc function
 * @name dockerUiApp.controller:CreditstatusCtrl
 * @description
 * # CreditstatusCtrl
 * Controller of the dockerUiApp
 */
angular.module('dockerUiApp')

  .controller('CreditstatusCtrl', ['$rootScope', '$scope', '$interval', 'CreditRequestsRestService', function ($rootScope, $scope, $interval, creditRequestsService) {

    $scope.loadData = function () {
      $scope.creditRequests = creditRequestsService.query();
    };

    var stop;
    var counter = 100;
    
    $scope.startAutoReload = function () {
      // Don't start a new autoReload when that is already done
      if (angular.isDefined(stop)) {
        return;
      }
      
      stop = $interval(function () {
        if(counter===100) {
          $scope.loadData();
          counter=0;
        }
        counter = counter + 5;
        angular.element('#reloadprogressbar').css('width', counter+'%').attr('aria-valuenow', counter);
      }, 166);
    };

    $scope.stopAutoReload = function () {
      if (angular.isDefined(stop)) {
        $interval.cancel(stop);
        stop = undefined;
      }
    };

    $scope.toggleAutoReload = function() {
      if (angular.isDefined(stop)) {
        $scope.stopAutoReload();
      } else {
        counter = 100;
        $scope.startAutoReload();
      }
    };

    $scope.startAutoReload();
    angular.element('[data-toggle="tooltip"]').tooltip();
    
    $scope.$on('$destroy', function () {
      // Make sure that the interval is destroyed too
      $scope.stopAutoReload();
    });
  }]);

