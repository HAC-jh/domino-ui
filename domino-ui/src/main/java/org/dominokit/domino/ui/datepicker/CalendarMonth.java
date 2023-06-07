package org.dominokit.domino.ui.datepicker;

import elemental2.dom.HTMLDivElement;
import org.dominokit.domino.ui.elements.DivElement;
import org.dominokit.domino.ui.utils.BaseDominoElement;

import java.util.Date;

import static java.util.Objects.isNull;

public class CalendarMonth extends BaseDominoElement<HTMLDivElement, CalendarMonth> implements CalendarStyles, CalendarViewListener {

    private final IsCalendar calendar;
    private MonthData monthData;
    private Date date;
    private CalendarDay[] monthDays = new CalendarDay[42];
    private DivElement root;
    private CalendarDay selectedDay;

    public CalendarMonth(IsCalendar calendar) {
        this.calendar = calendar;
        this.calendar.bindCalenderViewListener(this);
        this.date = this.calendar.getDate();
        this.root = div().addCss(dui_calendar_month);
        init(this);
        setDate(this.date);
    }

    public static CalendarMonth create(IsCalendar calendar){
        return new CalendarMonth(calendar);
    }

    private void setDate(Date date) {
        if(isNull(date)) {
            this.date = new Date();
        }else {
            this.date = date;
        }
        updateView();
    }

    private void updateView() {
        this.root.clearElement();
        Date tempDate = new Date(this.date.getYear(), this.date.getMonth(), 1);

        int monthFirstDay = tempDate.getDay() == 0 ? 7 : tempDate.getDay();

        this.root.appendChild(div()
                .addCss(dui_month_days_header)
                .apply(daysHeader -> {
                    for (int i = 0; i < 7; i++) {
                        daysHeader.appendChild(WeekDayHeader.create(this.calendar, i));
                    }
                })
        );
        this.monthData = new MonthData(this.date);
        MonthData monthBefore = monthData.getMonthBefore();
        MonthData monthAfter = monthData.getMonthAfter();

        int diff = monthFirstDay + 1;
        int[] currentDaysCounter = new int[]{0};
        int[] monthBeforeDaysCounter = new int[]{monthBefore.getDaysCount() - monthFirstDay + 1};
        int[] monthAfterDaysCounter = new int[]{1};

        int[] index = new int[]{0};
        for (int i = 0; i < 6; i++) {
            this.root
                    .appendChild(div()
                    .addCss(dui_month_days_row)
                    .apply(daysRow -> {
                        for (int day = 0; day < 7; day++) {
                            CalendarDay calendarDay;
                            if (index[0] < monthFirstDay) {
                                calendarDay = CalendarDay.create(this.calendar, new Date(monthBefore.getYear(), monthBefore.getMonth(), monthBeforeDaysCounter[0]), day, false);
                                monthBeforeDaysCounter[0] = monthBeforeDaysCounter[0] + 1;
                            } else if (index[0] >= monthFirstDay && index[0] < (monthData.getDaysCount() + diff - 1)) {
                                calendarDay = CalendarDay.create(this.calendar,new Date(monthData.getYear(), monthData.getMonth(), currentDaysCounter[0] + 1), day, true);
                                currentDaysCounter[0] = currentDaysCounter[0] + 1;
                            } else {
                                calendarDay = CalendarDay.create(this.calendar,new Date(monthAfter.getYear(), monthAfter.getMonth(), monthAfterDaysCounter[0]), day, false);
                                monthAfterDaysCounter[0] = monthAfterDaysCounter[0] + 1;
                            }

                            calendarDay.addClickListener(evt -> {
                               CalendarDay oldDay = this.selectedDay;
                               this.selectedDay = calendarDay;
                               this.calendar.getDateSelectionListeners().forEach(listener -> listener.onDaySelected(oldDay, this.selectedDay));
                            });
                            daysRow.appendChild(calendarDay);
                            this.calendar.getConfig().getPlugins().forEach(plugin -> {
                                plugin.onCalendarDayAdded(calendarDay);
                            });
                            monthDays[index[0]] = calendarDay;
                            index[0] = index[0] + 1;
                        }
                    })
            );
        }
    }

    @Override
    public HTMLDivElement element() {
        return this.root.element();
    }

    @Override
    public void onUpdateCalendarView(Date date) {
        setDate(date);
    }

    @Override
    public void onDateSelectionChanged(Date date) {
        setDate(date);
    }
}
