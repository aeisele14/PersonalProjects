/**
 * Main AngularJS Web Application
 */
var app = angular.module('hudlInterviewWebApp', ['ngRoute'])
  //Controller to access npr web api for querying stories
  .controller('queryController', function ($scope, $location, $http) {

    var apiKey = "MDEwOTcxMDAxMDE0MTc0NjI4NTYyMDk2MA001";
    
    var url = "http://api.npr.org/query?id=1001&fields=title&output=JSON&numResults=20&apiKey=" + apiKey;

    $http.get(url)
       .success(function (data) {
           $scope.stories = data;
       })
  });

/**
 * Configure the Routes
 */
app.config(['$routeProvider', function ($routeProvider) {
  $routeProvider
    // Home
    .when("/", {templateUrl: "partials/npretweet.html", controller: "queryController"})
    // else 404
    .otherwise("/404", {templateUrl: "partials/404.html", controller: "PageCtrl"});
}]);

/**
 * Controls all Pages
 */
app.controller('PageCtrl', function (/* $scope, $location, $http */) {
  console.log("Page Controller");

});