/* Chinese translation for the jQuery Timepicker Addon */
jQuery(function($) {
      $.timepicker.regional['zh-CN'] = {
          currentText: '现在',
          closeText: '完成',
          ampm: false,
          amNames: ['上午', 'AM'],
          pmNames: ['下午', 'PM'],
          timeFormat: 'hh:mm tt',
          timeSuffix: '',
          timeOnlyTitle: '选择时间',
          timeText: '时间',
          hourText: '时',
          minuteText: '分',
          secondText: '秒',
          millisecText: '毫秒',
          timezoneText: '时区',
      };
      $.timepicker.setDefaults($.timepicker.regional['zh-CN']);
 });