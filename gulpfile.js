var gulp = require('gulp');
var del = require('del');
var minifyCss = require('gulp-minify-css');
var gulpif = require('gulp-if');
var uglify = require('gulp-uglify');
var npmDist = require('gulp-npm-dist');
var htmlmin = require('gulp-htmlmin');

gulp.task('default',  gulp.series(copyIndex,copyPartials,minifyAndCopyCss,minifyAndCopyJs,copylibs,copyHTML,copyPhp));

function clean(done) {
    del(['dist']);
    done(); 
}

function copyIndex(done) {
    return gulp.src('./index.html')
    	.pipe(gulp.dest('./dist', {overwrite: true}));
}

function copyPartials(done){
	return gulp.src('partials/*.html')
	.pipe(gulp.dest('dist/partials',{overwrtite: true}))
}

function minifyAndCopyCss(done){
	return gulp.src('./css/*.css')
		.pipe(gulpif('*.css', minifyCss()))
		.pipe(gulp.dest('./dist/css',{overwrite: true}))
}	

function minifyAndCopyJs(done){
	return gulp.src('./js/*.js')
		.pipe(gulpif('*.js', uglify()))
		.pipe(gulp.dest('./dist/js',{overwrite: true}))
}

function copylibs(done){
	return gulp.src(npmDist(), {base:'./node_modules'})
    .pipe(gulp.dest('./dist/node_modules',{overwrite:true}))
}

function copyPhp(done){
	return gulp.src('./php/*.php')
    .pipe(htmlmin({
      collapseWhitespace: true,
      ignoreCustomFragments: [ /<%[\s\S]*?%>/, /<\?[=|php]?[\s\S]*?\?>/ ]
    }))
    .pipe(gulp.dest('./dist/php'));
}

function copyHTML(done){
	return gulp.src('./partials/*.html')
	.pipe(htmlmin({
	      collapseWhitespace: true,
	      ignoreCustomFragments: [ /<%[\s\S]*?%>/]
	    }))
	.pipe(gulp.dest('./dist/partials'))
}
	 