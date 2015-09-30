/*
 * Pause jQuery plugin v0.1
 *
 * Copyright 2010 by Tobia Conforto <tobia.conforto@gmail.com>
 *
 * Based on Pause-resume-animation jQuery plugin by Joe Weitzel
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or(at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
(function(){var e=jQuery,f="jQuery.pause",d=1,b=e.fn.animate,a={};function c(){return new Date().getTime()}e.fn.animate=function(k,h,j,i){var g=e.speed(h,j,i);g.complete=g.old;return this.each(function(){if(!this[f]){this[f]=d++}var l=e.extend({},g);b.apply(e(this),[k,e.extend({},l)]);a[this[f]]={run:true,prop:k,opt:l,start:c(),done:0}})};e.fn.pause=function(){return this.each(function(){if(!this[f]){this[f]=d++}var g=a[this[f]];if(g&&g.run){g.done+=c()-g.start;if(g.done>g.opt.duration){delete a[this[f]]}else{e(this).stop();g.run=false}}})};e.fn.resume=function(){return this.each(function(){if(!this[f]){this[f]=d++}var g=a[this[f]];if(g&&!g.run){g.opt.duration-=g.done;g.done=0;g.run=true;g.start=c();b.apply(e(this),[g.prop,e.extend({},g.opt)])}})}})();


/**
 * jQuery.marquee - scrolling text horizontally
 * Date: 11/01/2013
 * @author Aamir Afridi - aamirafridi(at)gmail(dot)com / http://aamirafridi.com/jquery/jquery-marquee-plugin
 */
(function(a){a.fn.marquee=function(b){return this.each(function(){var j=a.extend({},a.fn.marquee.defaults,b),i=a(this),g,h,d,f;j=a.extend({},j,i.data());j.gap=j.duplicated?j.gap:0;i.wrapInner('<div class="js-marquee"></div>');var e=i.find(".js-marquee").css({"margin-right":j.gap,"float":"left"});if(j.duplicated){e.clone().appendTo(i)}i.wrapInner('<div style="width:100000px" class="js-marquee-wrapper"></div>');f=i.find(".js-marquee:first").width()+j.gap;g=i.find(".js-marquee-wrapper");h=i.width();j.speed=((parseInt(f,10)+parseInt(h,10))/parseInt(h,10))*j.speed;if(j.duplicated){j.speed=j.speed/2}var c=function(){if(!j.duplicated){g.css("margin-left",j.direction=="left"?h:"-"+f+"px");d={"margin-left":j.direction=="left"?"-"+f+"px":h}}else{g.css("margin-left",j.direction=="left"?0:"-"+f+"px");d={"margin-left":j.direction=="left"?"-"+f+"px":0}}i.trigger("beforeStarting");g.animate(d,j.speed,"linear",function(){i.trigger("finished");c()})};i.on("pause",function(){g.pause()});i.on("resume",function(){g.resume()});if(j.pauseOnHover){i.hover(function(){if(a.fn.pause){g.pause()}},function(){if(a.fn.resume){g.resume()}})}setTimeout(c,j.delayBeforeStart)})};a.fn.marquee.defaults={speed:5000,gap:20,delayBeforeStart:0,direction:"left",duplicated:false,pauseOnHover:false}})(jQuery);
