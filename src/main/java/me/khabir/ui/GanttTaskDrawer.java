package me.khabir.ui;

import me.khabir.entity.Task;

import javax.swing.*;
        import java.awt.*;
        import java.util.HashMap;
import java.util.Map;

public class GanttTaskDrawer extends JFrame implements TaskDrawer {

    private ChartPanel chartPanel;

    public GanttTaskDrawer() {
        this.chartPanel = new ChartPanel();
        setTitle("Gantt Chart Scheduler");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(chartPanel);
    }

    @Override
    public void draw(Map<Integer, Task> schedule) {
        chartPanel.setSchedule(schedule);
        pack(); // Pack the frame to fit the panel's preferred size
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    // Inner class for the drawing panel
    private class ChartPanel extends JPanel {
        private final int RECT_WIDTH = 40;
        private final int RECT_HEIGHT = 60;
        private final int PADDING = 25;
        private Map<Integer, Task> schedule;
        private Map<String, Color> taskColors;
        private int maxTime = 0;

        // --- CHANGE 1: Predefined color list ---
        private final Color[] PREDEFINED_COLORS = {
                new Color(255, 179, 186), // Light Pink
                new Color(255, 223, 186), // Light Orange
                new Color(255, 255, 186), // Light Yellow
                new Color(186, 255, 201), // Light Green
                new Color(186, 225, 255), // Light Blue
                new Color(204, 204, 255), // Light Purple
                new Color(255, 204, 229)  // Light Magenta
        };

        public ChartPanel() {
            this.schedule = new HashMap<>();
            this.taskColors = new HashMap<>();
            setBackground(Color.WHITE);
        }

        public void setSchedule(Map<Integer, Task> schedule) {
            this.schedule = schedule;
            if (schedule != null && !schedule.isEmpty()) {
                this.maxTime = schedule.keySet().stream().max(Integer::compare).orElse(0);
            }
            // Dynamically set the preferred size based on the schedule
            int panelWidth = (maxTime + 1) * RECT_WIDTH + PADDING * 2;
            int panelHeight = RECT_HEIGHT + PADDING * 3;
            setPreferredSize(new Dimension(panelWidth, panelHeight));
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (schedule == null || schedule.isEmpty()) {
                return;
            }

            Graphics2D g2d = (Graphics2D) g;

            // Make it smooth
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            // --- Drawing Loop ---
            for (int t = 0; t <= maxTime; t++) {
                int x = PADDING + t * RECT_WIDTH;
                int y = PADDING;

                Task task = schedule.get(t);
                Color taskColor;

                if (task != null) {
                    // --- CHANGE 1 (Part 2): Use the predefined color list ---
                    taskColor = taskColors.computeIfAbsent(task.getName(), k -> {
                        int colorIndex = taskColors.size() % PREDEFINED_COLORS.length;
                        return PREDEFINED_COLORS[colorIndex];
                    });

                    g2d.setColor(taskColor);
                    g2d.fillRect(x, y, RECT_WIDTH, RECT_HEIGHT);

                    // Draw Task Name
                    g2d.setColor(Color.BLACK);
                    drawCenteredString(g2d, task.getName(), x, y, RECT_WIDTH, RECT_HEIGHT);
                } else {
                    // IDLE task
                    taskColor = new Color(240, 240, 240); // Light gray
                    g2d.setColor(taskColor);
                    g2d.fillRect(x, y, RECT_WIDTH, RECT_HEIGHT);

                    // Draw "IDLE"
                    g2d.setColor(Color.GRAY);
                    drawCenteredString(g2d, "IDLE", x, y, RECT_WIDTH, RECT_HEIGHT);
                }

                // Draw rectangle border
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.drawRect(x, y, RECT_WIDTH, RECT_HEIGHT);

                // --- CHANGE 2: Move time label logic ---
                // We draw the label centered on the LEFT edge (at 'x')
                g2d.setColor(Color.DARK_GRAY);
                g2d.setFont(new Font("SansSerif", Font.PLAIN, 12));
                String time = String.valueOf(t);
                int timeWidth = g2d.getFontMetrics().stringWidth(time);
                g2d.drawString(time, x - (timeWidth / 2), y + RECT_HEIGHT + 20);
            }

            // --- CHANGE 2 (Part 2): Draw the FINAL time label ---
            // This draws the label at the end of the very last box
            int final_t = maxTime + 1;
            int final_x = PADDING + final_t * RECT_WIDTH;
            String time = String.valueOf(final_t);
            int timeWidth = g2d.getFontMetrics().stringWidth(time);
            g2d.drawString(time, final_x - (timeWidth / 2), PADDING + RECT_HEIGHT + 20);
        }

        // Helper to draw text in the center of a box
        private void drawCenteredString(Graphics2D g, String text, int x, int y, int width, int height) {
            Font font = new Font("SansSerif", Font.BOLD, 14);
            g.setFont(font);
            FontMetrics metrics = g.getFontMetrics(font);
            int textX = x + (width - metrics.stringWidth(text)) / 2;
            int textY = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
            g.drawString(text, textX, textY);
        }
    }
}