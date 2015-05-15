		
		$(document).ready(function () {
			
		    $(".data").click(function () {
		        $(this).val('');
		    });
		    var x = $("#mvname").val();
		    var a = x.split(',');
		    $.each(a,function(i,j){
		    	calldata1(j);
		    	return;
		    });
		    
		   // calldata1("Inception");
		    
		    $(".tabledata").hide();
		    $(".search-button").click(calldata);
		    $("#loading").hide();
		    $(window).keydown(function (e){
		        if(e.keyCode == 13)
		        {
		            calldata();
		            return false;
		        }
		    });
		});
		
		
//		$(document).ready(function () {
//		    $(".data").click(function () {
//		        $(this).val('');
//		    });
//		    $(".tabledata").hide();
//		    $(".search-button").click(calldata);
//		    $("#loading").hide();
//		    $(window).keydown(function (e){
//		        if(e.keyCode == 13)
//		        {
//		            calldata();
//		            return false;
//		        }
//		    });
//		});

		function calldata() {
		    console.log("in call data function");
		    var name = $(".data").val();
		    if (name === "" || name === null || typeof name === "undefined" || name === "Search...")
		        return;
		    rottenTomatoesMovieSearch(name, renderResults);
		}
		
		function calldata1(mvname) {
		    console.log("in call data function");
		    var name = mvname;
		    if (name === "" || name === null || typeof name === "undefined" || name === "Search...")
		        return;
		    rottenTomatoesMovieSearch(name, renderResults1);
		}


		function rottenTomatoesMovieSearch(moviename, callback) {
		    var key = "d9aufby78am3srf5axmyspxj";
		    $.ajax({
		        url: "http://api.rottentomatoes.com/api/public/v1.0/movies.json",
		        dataType: "jsonp",
		        data: { apikey: key, q: moviename, page_limit: 10 },

		        success: function (response) {
		            //alert("Enter");
		            $('#loading').hide();
		            
		            console.log(response);
		            if (typeof callback === "function")
		                callback(response,moviename);
		        }
		    });
		}


		function renderResults(response,name) {
		    var movies = response.movies;
		    var table = $(".table-row");
		    table.empty();
		    if (movies.length == 0) {
		        document.getElementById("tble").className = "table invisible";
		        alert("Please enter a valid name"); 
		    }
		    else {
		        document.getElementById("tble").className = "table visible";
		        for (var i in movies) {
		            var movie = movies[i];
		            var title = movie.title;
		            var synopsis = movie.synopsis;
		            var rating = movie.ratings.critics_rating;
		            var score = movie.ratings.critics_score;
		            var tr = $("<tr>");
		            var td = $("<td>");
		            td.append(title);
		            tr.append(td);
		            td = $("<td>");
		            if (synopsis != null) {
		                td.append(synopsis);
		            }
		            if (synopsis == null || synopsis == '' || synopsis == "") {
		                td.append("No data found");
		            }
		            tr.append(td);
		            td = $("<td>");
		            if (rating != null) {
		                td.append(rating);
		            }
		            if (rating == null || rating == '' || rating == "") {
		                td.append("No data found");
		            }
		            tr.append(td);
		            td = $("<td>");
		            td.append(score);
		            tr.append(td);
		            td = $("<td>");
		            var link = "getmore.aspx?name=" + title;
		            var url = $("<a>");
		            url.attr("href", link);

		            var imginfo = $("<img src=\"../images/Project/GetMoreInfoButton.jpg\" class=\"like_button\">");
		            url.append(imginfo);

		            url.attr("target", "_blank");
		            td.append(url);
		            tr.append(td);
		            td = $("<td>");

		            var link = "add_to_fav.aspx?title=" + title;
		            var url = $("<a>");
		            url.attr("href", link);
		            var img = $("<img src=\"../images/Project/Like.jpg\" class=\"like_button\">");
		            url.append(img);
		            url.attr("target", "_blank");
		            var u_name = document.getElementById("user").outerText.toString();
		            if (u_name != "Guest") {
		                td.append(url);
		            }
		            tr.append(td);
		            table.append(tr);
		            
		        }
		    }
		}
		
		function renderResults1(response,name) {
		    var movies = response.movies;
		  //  alert(name);
		    if (movies.length == 0) {
		        
		//        alert("Please enter a valid name"); 
		    }
		    else {
		        for (var i in movies) {
		            var movie = movies[i];
		            if(!name.match(movie.title)){
		            	continue;
		            }
		            var arr = [
		                          movie.title, 
		                          movie.synopsis, 
		                          movie.ratings.critics_rating,
		                          movie.ratings.critics_score];
		            //alert(movie.posters.original);
		            if(movie.posters != null && !movie.posters.thumbnail.match(/_default/)) 
		             {
		            	var clss = "";
		            	if(index == 0) clss = "first";
		            	var img ="<img src=\""+movie.posters.thumbnail+"\" id = "+(index++)+" class=\""+clss+"\">";
		            	$("#tb").append(img);
		            	
		             }
		        }
		    }
		}
	var index = 0;