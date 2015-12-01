(function($) {
    $.fn.simpleAccordion = function(options) {
        //options
        var opts = $.extend(
                {},
                {open: false},
        {animationIn: 'fadeInLeft'},
        {animationOut: 'fadeOutLeft'},
        options);

        //styles
        $(this).css({
            '-webkit-mask-image': 'url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAIAAACQd1PeAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAA5JREFUeNpiYGBgAAgwAAAEAAGbA+oJAAAAAElFTkSuQmCC)', /* this fixes the overflow:hidden in Chrome/Opera */
            '-o-mask-image': 'url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAIAAACQd1PeAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAA5JREFUeNpiYGBgAAgwAAAEAAGbA+oJAAAAAElFTkSuQmCC)', /* this fixes the overflow:hidden in Chrome/Opera */
            '-moz-mask-image': 'url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAIAAACQd1PeAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAA5JREFUeNpiYGBgAAgwAAAEAAGbA+oJAAAAAElFTkSuQmCC)', /* this fixes the overflow:hidden in Chrome/Opera */
            'mask-image': 'url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAIAAACQd1PeAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAA5JREFUeNpiYGBgAAgwAAAEAAGbA+oJAAAAAElFTkSuQmCC)' /* this fixes the overflow:hidden in Chrome/Opera */

        });

        //functionalities
        return this.each(function() {
            var dts = $(this).children('dt');
            dts.click(sa_click);
            dts.each(sa_reset);
            if (opts.open)
                $(this).children('dt:first-child').next().show();
        });

        function sa_click() {
            $(this).siblings('dt').each(sa_hide);

            if ($(this).hasClass('isClose')) {
                $(this).next().slideDown('fast').removeClass(opts.animationOut).addClass('animated ' + opts.animationIn);
                $(this).removeClass('isClose').addClass('isOpen');
                return false;
            } else if ($(this).hasClass('isOpen')) {
                $(this).next().removeClass(opts.animationIn).addClass('animated ' + opts.animationOut).slideUp('slow');
                $(this).removeClass('isOpen').addClass('isClose');
                return false;
            }
        }

        function sa_hide() {
            $(this).next().slideUp('fast');
        }

        function sa_reset() {
            $(this).addClass('isClose');
            $(this).next().hide();
        }

    }
})(jQuery);