package me.writeily.pro.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import me.writeily.pro.NoteActivity;
import me.writeily.pro.R;

/**
 * Created by jeff on 15-02-04.
 */
public class WriteilyWidgetProvider extends AppWidgetProvider {
    @Override
    public void onDeleted(Context context, int[] appWidgetIds)
    {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onDisabled(Context context)
    {
        super.onDisabled(context);
    }

    @Override
    public void onEnabled(Context context)
    {
        super.onEnabled(context);
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        super.onReceive(context, intent);
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i = 0; i < N; i++) {
            int appWidgetId = appWidgetIds[i];

            // Create an Intent to launch NoteActivity
            Intent newNoteIntent = new Intent(context, NoteActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, newNoteIntent, 0);

            // Get the layout for the App Widget and attach an on-click listener to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

            // New Note button
            views.setOnClickPendingIntent(R.id.widget_new_note, pendingIntent);

            // ListView
            Intent notesListIntent = new Intent(context, FilesWidgetService.class);
            notesListIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
            notesListIntent.setData(Uri.parse(notesListIntent.toUri(Intent.URI_INTENT_SCHEME)));

            views.setEmptyView(R.id.widget_list_container, R.id.widget_empty_hint);
            views.setRemoteAdapter(R.id.widget_notes_list, notesListIntent);

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }
}
