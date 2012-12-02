import connector.Connector;
import domain.manager.FENManager;
import domain.manager.MetadataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

public class Runner {

    private static Runner instance;
    public static Runner INSTANCE = instance();

    private static Runner instance() {
        if(instance == null) {
            return instance = new Runner();
        }
        return instance;
    }

    private Runner() {}

    private List<JComponent> components(List<String> fenStrings) {
        List<JComponent> components = new LinkedList<JComponent>();
        for(String fen : fenStrings) {
            components.add(Connector.connect(fen));
        }
        return components;
    }

    public List<JComponent> allDistinctSolutions() {
        List<String> fenStrings = FENManager.distinct();
        return components(fenStrings);
    }

    public List<JComponent> allUniqueSolutions() {
        return components(FENManager.unique());
    }

    public JFrame generateDistinct() {
        final List<String> meta = MetadataManager.external();
        return new ChessFrame() {

            @Override
            protected List<JComponent> getSolutions() {
                return allDistinctSolutions();
            }

            @Override
            protected String getInfoText() {
                return "Solution : " + current + " " + meta.get(current);
            }
        };
    }

    public JFrame generateUnique() {
        return new ChessFrame() {

            @Override
            protected List<JComponent> getSolutions() {
                return allUniqueSolutions();
            }

            @Override
            protected String getInfoText() {
                return "Solution : " + current;
            }
        };
    }

    private abstract static class ChessFrame extends JFrame {
            protected List<JComponent> components;
            protected int current;

            private JButton next;
            private JButton prev;
            protected JLabel info;

            {
                setTitle("8 queens problem");

                components = getSolutions();
                current = 0;
                getContentPane().setLayout(new BorderLayout());
                add(components.get(current), BorderLayout.CENTER);

                next = new JButton(">");
                prev = new JButton("<");

                next.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        clickHandler(new ICallback() {
                            @Override
                            public void eval() {
                                current = current < components.size() - 1 ? current + 1 : current;
                            }
                        });
                    }
                });

                prev.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        clickHandler(new ICallback() {
                            @Override
                            public void eval() {
                                current = current == 0 ? current : current - 1;
                            }
                        });
                    }
                });

                add(new JPanel() {
                    {
                        setLayout(new BorderLayout());

                        add(new JPanel() {
                            {
                                info = new JLabel(getInfoText());
                                setLayout(new BorderLayout());
                                add(info, BorderLayout.EAST);
                            }
                        }, BorderLayout.WEST);
                        add(new JPanel() {
                            {
                                setLayout(new BorderLayout());
                                add(next, BorderLayout.EAST);
                                add(prev, BorderLayout.WEST);
                            }
                        }, BorderLayout.EAST);
                    }
                }, BorderLayout.SOUTH);

                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                setSize(600, 600);
                setVisible(true);
                pack();
            }

        protected abstract List<JComponent> getSolutions();

        protected abstract String getInfoText();

        private void clickHandler(ICallback currentCallback) {
            remove(components.get(current));
            currentCallback.eval();
            add(components.get(current));
            info.setText(getInfoText());
            validate();
            repaint();
        }
    }

    private interface ICallback {
        void eval();
    }
}
