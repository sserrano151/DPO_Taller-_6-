package uniandes.dpoo.swing.interfaz.mapa;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import uniandes.dpoo.swing.interfaz.principal.VentanaPrincipal;
import uniandes.dpoo.swing.mundo.Restaurante;

@SuppressWarnings("serial")
public class VentanaMapa extends JFrame implements ActionListener
{
    /**
     * El comando para reconocer al radio que muestra sólo los restaurantes visitados
     */
    private static final String VISITADOS = "VISITADOS";

    /**
     * El comando para reconocer al radio que muestra todos los restaurantes
     */
    private static final String TODOS = "TODOS";

    /**
     * El panel con el mapa
     */
    private PanelMapaVisualizar panelMapa;

    /**
     * El radio button para hacer que se muestren todos los restaurantes. Si este está activo, radioVisitados debe estar inactivo.
     */
    private JRadioButton radioTodos;

    /**
     * El radio button para hacer que se muestren sólo los restaurantes visitados. Si este está activo, radioTodos debe estar inactivo.
     */
    private JRadioButton radioVisitados;

    /**
     * La referencia a la ventana principal
     */
    private VentanaPrincipal ventanaPrincipal;

    public VentanaMapa( VentanaPrincipal ventanaPrincipal, List<Restaurante> restaurantes )
    {
        this.ventanaPrincipal = ventanaPrincipal;

        setLayout(new BorderLayout());
        panelMapa = new PanelMapaVisualizar();
        panelMapa.actualizarMapa(restaurantes);
        add(panelMapa, BorderLayout.CENTER);
        
        JPanel pNorte = new JPanel();

        JRadioButton rbTodos = new JRadioButton("Todos");
        rbTodos.setActionCommand(TODOS);
        rbTodos.setSelected(true);
        rbTodos.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                panelMapa.actualizarMapa(ventanaPrincipal.getRestaurantes(true));
                panelMapa.repaint();
            }
        });
        
        JRadioButton rbVisitados = new JRadioButton("Visitados");
        rbVisitados.setActionCommand(VISITADOS);
        rbVisitados.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                panelMapa.actualizarMapa(ventanaPrincipal.getRestaurantes(false));
                panelMapa.repaint();
            }
        });

        ButtonGroup g = new ButtonGroup();
        g.add(rbTodos);
        g.add(rbVisitados);

        pNorte.add(rbTodos);
        pNorte.add(rbVisitados);
        add(pNorte, BorderLayout.NORTH);

        // Termina de configurar la ventana y la muestra
        pack( );
        setResizable( false );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setLocationRelativeTo( null );
    }

    @Override
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( TODOS.equals( comando ) )
        {
            panelMapa.actualizarMapa( ventanaPrincipal.getRestaurantes( true ) );
        }
        else if( VISITADOS.equals( comando ) )
        {
            panelMapa.actualizarMapa( ventanaPrincipal.getRestaurantes( false ) );
        }
    }

}
