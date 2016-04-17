
import MovieUILogic.FileListGenerator;
import MovieUILogic.FileLoader;
import MovieUILogic.IMDB_Miner;
import MovieUILogic.ImageClicker;
import MovieUILogic.MovieUISaver;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import MovieUILogic.VideoFile;
import MovieUILogic.VideoFileLoaderSaver;

public class MovieInterface extends javax.swing.JFrame {

    ArrayList<VideoFile> movieList = new ArrayList();

    String saveDirectory = "/movie_UI/UI_saves";
    String imageSaveDirectory = "/images";
    String saveVideoFileName = "VideoFiles_UIsaver.kev";
    String saveUIFileName = "VideoFiles_UI_UISettings.kev";
    MovieUISaver UIsaver = new MovieUISaver(saveDirectory);
    IMDB_Miner miner;
    FileLoader fileLoader;
    VideoFileLoaderSaver videoFileLoader;
    FileListGenerator sorter;

    int panelWidth;
    int panelHeight;
    boolean imagesLoaded = false;
    boolean imageSizer = false;
    boolean activeDirectory;
    boolean titleView = true;
    boolean yearView = true;
    boolean ratingView = false;
    boolean editEnabled = false;
    boolean viewCountView = false;
    double imageScale = .7;
    boolean movieView = true;
    boolean seriesView = true;
    boolean genreSort = false;
    boolean videoFilesLoaded = false;
    boolean bufferedImagesLoaded = false;
    boolean UIunPaused = true;

    String genreSelect;
    String sortOption;
    String fileDirectory = "Not Set";
    String searchTerm = "";

    public MovieInterface() {

        UIsaver.loadState(saveVideoFileName);
        fileLoader = new FileLoader(imageSaveDirectory);
        miner = new IMDB_Miner(fileLoader);
        titleView = UIsaver.getTitleViewSetting();
        imageScale = UIsaver.getImageScale();
        yearView = UIsaver.getYearViewSetting();
        ratingView = UIsaver.getRatingViewSetting();
        fileDirectory = UIsaver.getMovieDirectory();
        videoFileLoader = new VideoFileLoaderSaver(fileDirectory, fileLoader);
        viewCountView = UIsaver.getPlayCountSetting();
        sorter = new FileListGenerator();
        initComponents();

        jScrollPaneDisplay.getVerticalScrollBar().setUnitIncrement(16);

        panelWidth = jScrollPaneDisplay.getWidth();
        jSliderImageSize.setVisible(false);
        jButtonSaveSize.setVisible(false);
        jPanel1.setBackground(Color.GRAY);
        jScrollPaneDisplay.setBackground(Color.RED);

        if (fileLoader.checkForVideoFileSave(fileDirectory, saveVideoFileName)) {
            System.out.println("saved video file found.");
            loadVideoFiles(saveVideoFileName);

        } else {
            System.out.println("save not found");
        }
     
        genreSelect = jComboBox2.getItemAt(jComboBox2.getSelectedIndex());
        sortOption = jComboBoxSorter.getItemAt(jComboBoxSorter.getSelectedIndex());
        startUIProgram();

    }

    public void startUIProgram() {
        System.out.println("startUIProgram, loadImages");
        loadImages();

        System.out.println("startUI, genresBox");
        genresBoxCheck();
        paintUIBG();
        sortingCheck();
        System.out.println("startUI, runUI");
        runUICode();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanelPrep = new javax.swing.JPanel();
        jScrollPaneDisplay = new javax.swing.JScrollPane();
        jComboBoxSorter = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jSliderImageSize = new javax.swing.JSlider();
        jButtonSaveSize = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabelDisplayCount = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuMovieSetup = new javax.swing.JMenu();
        jMenuDirectoryPick = new javax.swing.JMenuItem();
        jMenuUpdateData = new javax.swing.JMenuItem();
        jMenuDownloadData = new javax.swing.JMenuItem();
        jCheckBoxMenuItemEnableEdit = new javax.swing.JCheckBoxMenuItem();
        jMenuDisplay = new javax.swing.JMenu();
        jCheckBoxMenuTitlesView = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuYearsView = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuRatingView = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuPlayCount = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuGenresView = new javax.swing.JCheckBoxMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemSetImageSize = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(100, 100));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanelPrep.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanelPrepLayout = new javax.swing.GroupLayout(jPanelPrep);
        jPanelPrep.setLayout(jPanelPrepLayout);
        jPanelPrepLayout.setHorizontalGroup(
            jPanelPrepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelPrepLayout.setVerticalGroup(
            jPanelPrepLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jScrollPaneDisplay.setBackground(new java.awt.Color(51, 51, 51));
        jScrollPaneDisplay.setForeground(new java.awt.Color(51, 51, 51));
        jScrollPaneDisplay.setHorizontalScrollBar(null);
        jScrollPaneDisplay.setOpaque(false);
        jScrollPaneDisplay.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jScrollPaneDisplayComponentResized(evt);
            }
        });

        jComboBoxSorter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rating", "Title", "Year", "Views" }));
        jComboBoxSorter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSorterActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("Sort");

        jTextField1.setText("Search");
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jSliderImageSize.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderImageSizeStateChanged(evt);
            }
        });

        jButtonSaveSize.setLabel("Apply Size");
        jButtonSaveSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveSizeActionPerformed(evt);
            }
        });

        jComboBox2.setMaximumRowCount(26);
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Files", "Movies", "TV Series", "Action", "Adventure", "Animation", "Biography", "Comedy", "Crime", "Documentary", "Drama", "Family", "Fantasy", "Film-Noir", "History", "Horror", "Music", "Musical", "Mystery", "Romance", "Sci-Fi", "Sport", "Thriller", "War", "Western" }));
        jComboBox2.setSelectedItem(UIsaver.getMenuGenre());
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setText("Genre");

        jLabelDisplayCount.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPaneDisplay)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jSliderImageSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSaveSize)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxSorter, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanelPrep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabelDisplayCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelPrep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSliderImageSize, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxSorter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(jButtonSaveSize, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelDisplayCount, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jMenuMovieSetup.setBackground(new java.awt.Color(51, 51, 51));
        jMenuMovieSetup.setText("Movie Setup |");
        jMenuMovieSetup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuMovieSetupActionPerformed(evt);
            }
        });

        jMenuDirectoryPick.setText("Set Movie Directory");
        jMenuDirectoryPick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuDirectoryPickActionPerformed(evt);
            }
        });
        jMenuMovieSetup.add(jMenuDirectoryPick);

        jMenuUpdateData.setText("Import My Movies");
        jMenuUpdateData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuUpdateDataActionPerformed(evt);
            }
        });
        jMenuMovieSetup.add(jMenuUpdateData);

        jMenuDownloadData.setText("Download Covers & Info");
        jMenuDownloadData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuDownloadDataActionPerformed(evt);
            }
        });
        jMenuMovieSetup.add(jMenuDownloadData);

        jCheckBoxMenuItemEnableEdit.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxMenuItemEnableEdit.setText("Enable Editing");
        jCheckBoxMenuItemEnableEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemEnableEditActionPerformed(evt);
            }
        });
        jMenuMovieSetup.add(jCheckBoxMenuItemEnableEdit);

        jMenuBar1.add(jMenuMovieSetup);

        jMenuDisplay.setText("Display |");

        jCheckBoxMenuTitlesView.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuTitlesView.setSelected(UIsaver.getTitleViewSetting());
        jCheckBoxMenuTitlesView.setText("Titles");
        jCheckBoxMenuTitlesView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuTitlesViewActionPerformed(evt);
            }
        });
        jMenuDisplay.add(jCheckBoxMenuTitlesView);

        jCheckBoxMenuYearsView.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuYearsView.setSelected(UIsaver.getYearViewSetting());
        jCheckBoxMenuYearsView.setText("Year");
        jCheckBoxMenuYearsView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuYearsViewActionPerformed(evt);
            }
        });
        jMenuDisplay.add(jCheckBoxMenuYearsView);

        jCheckBoxMenuRatingView.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuRatingView.setSelected(UIsaver.getRatingViewSetting());
        jCheckBoxMenuRatingView.setText("Rating");
        jCheckBoxMenuRatingView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuRatingViewActionPerformed(evt);
            }
        });
        jMenuDisplay.add(jCheckBoxMenuRatingView);

        jCheckBoxMenuPlayCount.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuPlayCount.setSelected(UIsaver.getPlayCountSetting());
        jCheckBoxMenuPlayCount.setText("Views");
        jCheckBoxMenuPlayCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuPlayCountActionPerformed(evt);
            }
        });
        jMenuDisplay.add(jCheckBoxMenuPlayCount);

        jCheckBoxMenuGenresView.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuGenresView.setSelected(UIsaver.getGenreViewSetting());
        jCheckBoxMenuGenresView.setText("Genres");
        jCheckBoxMenuGenresView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuGenresViewActionPerformed(evt);
            }
        });
        jMenuDisplay.add(jCheckBoxMenuGenresView);
        jMenuDisplay.add(jSeparator1);

        jMenuItemSetImageSize.setText("Set Image Size...");
        jMenuItemSetImageSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSetImageSizeActionPerformed(evt);
            }
        });
        jMenuDisplay.add(jMenuItemSetImageSize);

        jMenuBar1.add(jMenuDisplay);

        jMenuHelp.setText("Help");

        jMenuItem3.setText("About..");
        jMenuHelp.add(jMenuItem3);

        jMenuItem4.setText("TroubleShoot...");
        jMenuHelp.add(jMenuItem4);

        jMenuBar1.add(jMenuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void runUICode() {
        //System.out.println("runUI called");
        System.out.println("run UI called");
        setDisplayLabel(0);
        System.out.println("List size: " + movieList.size());
        if (movieList.size() > 0) {
            printUI();
        }
    }

    public void paintUIBG() {
        jPanelPrep.setBackground(Color.BLACK);
        jScrollPaneDisplay.setBackground(Color.RED);
    }
    
    public void saveUI(){
        UIsaver.saveUIState(saveUIFileName);
    }

   
    public void prepareJPanelPrep() {
        jPanelPrep.setPreferredSize(new Dimension(jScrollPaneDisplay.getWidth(), jScrollPaneDisplay.getHeight()));
        jPanelPrep.setSize(jScrollPaneDisplay.getWidth(), jScrollPaneDisplay.getHeight());
        jPanelPrep.removeAll();
        jPanelPrep.updateUI();
    }

    public int setSpace() {
        int space = 20;
        if ((!yearView && !viewCountView) || (!titleView && !ratingView) || (!yearView && !ratingView) || (!titleView && !viewCountView)) {
            space -= 20;
        }

        if (!titleView && !ratingView && !yearView && !viewCountView) {
            space = -20;
        }
        return space;
    }

    public int setYearLocY() {
        if (!titleView) {
            return 50;
        }
        return 30;
    }

    public int setViewsLocY() {
        if (!ratingView) {
            return 50;
        }
        return 30;
    }

    public boolean displaySeriesOrMovies(VideoFile file) {
        if (movieView && file.isMovie()) {
            return true;
        } else if (movieView && seriesView) {
            return true;
        } else if (seriesView && !file.isMovie()) {
            return true;
        }
        return false;
    }

    public boolean displayThisGenre(VideoFile file) {
        if (!genreSort) {
            return true;
        } else if (genreSort && file.getGenres().toString().contains(genreSelect)) {
            return true;
        }
        return false;
    }

    public int setGapSpacer(int imagesAcross, int imageWidth) {
        return ((jPanelPrep.getWidth() - 10) - (imagesAcross * imageWidth)) / (imagesAcross + 1);
    }

    public void createYearLabel(VideoFile file, int height, int yearLocY, int x, int y) {
        if (yearView && !editEnabled) {
            JLabel yearLabel = new JLabel();

            yearLabel.setText(file.getYear() + "");
            yearLabel.setForeground(Color.WHITE);
            yearLabel.setBounds(0, 0, 100, 100);

            yearLabel.setLocation(x, y + height - yearLocY);
            jPanelPrep.add(yearLabel);
        }
    }

    public void createTitleLabel(VideoFile file, int height, int titleLocation, int x, int y) {
        if (titleView && !editEnabled) {
            JLabel titleLabel = new JLabel();

            titleLabel.setText(file.getTitle());
            //titleLabel.setFont(BOLD);
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setBounds(0, 0, 150, 100);

            titleLabel.setLocation(x, y + height - titleLocation);
            jPanelPrep.add(titleLabel);
        }
    }

    public void createRatingLabel(VideoFile file, int height, int width, int ratingLocX, int ratingLocY, int x, int y) {
        if (ratingView && !editEnabled) {
            JLabel ratingLabel = new JLabel();

            ratingLabel.setText(file.getRating() + "");
            ratingLabel.setForeground(Color.WHITE);
            ratingLabel.setBounds(0, 0, 100, 100);

            ratingLabel.setLocation(x + width - ratingLocX, y + height - ratingLocY);
            jPanelPrep.add(ratingLabel);
        }
    }

    public void createViewCountLabel(VideoFile file, int height, int width, int viewsLocX, int viewsLocY, int x, int y) {
        if (viewCountView && !editEnabled) {
            JLabel viewsLabel = new JLabel();

            viewsLabel.setText(file.getPlaycount() + "");
            viewsLabel.setForeground(Color.WHITE);
            viewsLabel.setBounds(0, 0, 100, 100);

            viewsLabel.setLocation(x + width - viewsLocX, y + height - viewsLocY);
            jPanelPrep.add(viewsLabel);
        }
    }

    public void assignListeners() {
        //assign all the click listeners to the JLabels that are stored within the VideoFiles
        //the JLabels must already be created based on size at this point and assigned
        //need to recall this if image sizes are changed

        if (!imageSizer) {
            System.out.println("assigning Click Listeners");
            for (VideoFile movie : movieList) {
                if (!movie.getHasListener()) {
                    ImageClicker clickImage = new ImageClicker(fileDirectory, movie);
                    clickImage.addListener(movie.getCover());
                    movie.setHasListener();

                }
            }
        }
    }

    public void setCoverLabels(int width, int height) {
        //turns imageIcons into new JLabels
        //can only be done once ImageIcons are set
        int i = 0;
        System.out.println("setCoverLabels Called. Making JLabels");
        for (VideoFile movie : movieList) {
            if (imageSizer && i > 5) {
                break;
            }
            movie.setCover(width, height);
            i++;
        }
        assignListeners();
    }

    public void loadImages() {
        System.out.println("load Images called, setting Buffered Images");
        for (VideoFile movie : movieList) {
            System.out.println("loadImage in main: " + movie.getTitle() + " has image: " + movie.getHasImage());
            if (!movie.getHasImage()) {

                fileLoader.loadImage(movie);
            }
        }
    }

    public void sizeImages() {
        //sets all the image icons
        System.out.println("Main, Size Images called");
        int i = 0;
        for (VideoFile movie : movieList) {
            if (imageSizer && i > 2) {
                break;
            }
            fileLoader.sizeImages(movie, imageScale);
            i++;
        }
    }

    public void printUI() {
        System.out.println("UI Code Started.");
        if (UIunPaused) {

            if (!imagesLoaded) {
                sizeImages();
            }

            int height = movieList.get(0).getImageIcon().getIconHeight() + (int) (movieList.get(0).getImageIcon().getIconHeight() * .1);
            int width = movieList.get(0).getImageIcon().getIconWidth();

            if (!imagesLoaded) {
                setCoverLabels(width, height);
            }

            int printLoop = 0;
            int rowCount = 0;
            int x = 0;
            int printCount = 0;
            boolean spaceSet = false;

            //UI label locations
            int titleLocation = 50;
            int ratingLocX = 18;
            int ratingLocY = 50;
            int viewsLocX = 18;

            //make the underside gap smaller if these are turned off
            int space = setSpace();
            int yearLocY = setYearLocY();
            int viewsLocY = setViewsLocY();

            prepareJPanelPrep();

            int y = space;

            for (VideoFile file : movieList) {
                if (imageSizer && printCount > 1) {
                    System.out.println("image Sizer On, printCount exceeded");
                    break;
                }
                if (file.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {

                    if (displaySeriesOrMovies(file)) {
                        if (displayThisGenre(file)) {

                            JLabel cover;

                            int imagesAcross = jPanelPrep.getWidth() / width;
                            int gapSpacer = setGapSpacer(imagesAcross, width);

                            x += gapSpacer;

                            //override space to be the same as gapSpacer for minimalist even layout
                            //find a way to not need to do this every time
                            if (!spaceSet && (!titleView && !yearView && !viewCountView && !ratingView)) {
                                //spaceSet boolean stops this from being evaluated if it's already done
                                System.out.println("SPACESET TESTED");
                                space = gapSpacer - 30;
                                spaceSet = true;
                            }

                            if (printLoop >= imagesAcross) {
                                //System.out.println("reset");
                                rowCount++;
                                y += height + space;
                                x = gapSpacer;
                                printLoop = 0;
                            }
                            printLoop++;

                            //Positions and adding Year, Title, Ratings, ViewCount
                            createYearLabel(file, height, yearLocY, x, y);
                            createTitleLabel(file, height, titleLocation, x, y);
                            createRatingLabel(file, height, width, ratingLocX, ratingLocY, x, y);
                            createViewCountLabel(file, height, width, viewsLocX, viewsLocY, x, y);

                            cover = file.getCover();
                            cover.setLocation(x, y);
                            x += width;
                            printCount++;

                            jPanelPrep.add(cover);

                        }
                    }
                }
            }

            paintUIBG();
            
            jPanelPrep.setPreferredSize(new Dimension(jPanelPrep.getWidth(), ((height + space) * (rowCount + 1)) + 40));

            fillScrollPane(jPanelPrep);
            setDisplayLabel(printCount);

            imagesLoaded = true;
        }
    }

    public void fillScrollPane(JPanel preparedPanel) {
        jScrollPaneDisplay.setViewportView(preparedPanel);
    }

    public void setDisplayLabel(int printCount) {
        jLabelDisplayCount.setText("Directory: " + fileDirectory + "   |   Displaying " + printCount + " of " + movieList.size());
    }


    private void jScrollPaneDisplayComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jScrollPaneDisplayComponentResized
        //actions for Resize
        //sends new panelWidth and recalcualtes layout
        if (jScrollPaneDisplay.getWidth() > panelWidth + 20 || jScrollPaneDisplay.getWidth() < panelWidth - 20) {
            panelWidth = jScrollPaneDisplay.getWidth();
            System.out.println("Calling RUN UI from Resized");
            runUICode();
        }
    }//GEN-LAST:event_jScrollPaneDisplayComponentResized

    private void jMenuItemSetImageSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSetImageSizeActionPerformed
        imageSizer = true;
        imageSizer();
    }//GEN-LAST:event_jMenuItemSetImageSizeActionPerformed

    private void imageSizer() {
        jSliderImageSize.setValue((int) ((imageScale) * 100) - 50);
        jButtonSaveSize.setVisible(imageSizer);
        jSliderImageSize.setVisible(imageSizer);

    }
    private void jButtonSaveSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveSizeActionPerformed
        //Save Size from slider bar, hide bar and button
        imageSizer = false;
        imageSizer();

        imageScale = (((jSliderImageSize.getValue() + 50) * 1.00) / 100);
        UIsaver.setImageScale(imageScale);
        //UIsaver.saveUIState(saveUIFileName);
        saveUI();

        imagesLoaded = false;
        System.out.println("Calling RUN UI from save size");
        runUICode();
    }//GEN-LAST:event_jButtonSaveSizeActionPerformed

    private void jSliderImageSizeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderImageSizeStateChanged
        if (((jSliderImageSize.getValue() + 50) * 1.00) / 100 > imageScale + .02 || ((jSliderImageSize.getValue() + 50) * 1.00) / 100 < imageScale - .02) {
            imageScale = (jSliderImageSize.getValue() + 50) * 1.00 / 100;
            System.out.println("changed: " + imageScale);

            imagesLoaded = false;

            System.out.println("RUN UI from Image Slider");
            runUICode();
        }
    }//GEN-LAST:event_jSliderImageSizeStateChanged

    private void jMenuDirectoryPickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuDirectoryPickActionPerformed

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Choose Video Directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("Chooser: " + chooser.getSelectedFile().toString() + " fileDirectory: " + fileDirectory);
            if (!chooser.getSelectedFile().toString().equals(fileDirectory)) {
                imagesLoaded = false;
                movieList.clear();
                fileLoader.clearStoredLists();
                System.out.println("list cleared size: " + movieList.size());
                fileDirectory = chooser.getSelectedFile().toString();

                UIsaver.setDirectory(fileDirectory);
                videoFileLoader.setNewPathName(fileDirectory);
                loadVideoFiles(saveVideoFileName);

                //UIsaver.saveUIState(saveUIFileName);
                saveUI();
                System.out.println("Calling RUN UI from directory pick");

            }
        } else {
            //System.out.println("no selection");
        }

    }//GEN-LAST:event_jMenuDirectoryPickActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        //Search Box
        searchTerm = jTextField1.getText();
        runUICode();
        System.out.println("searching: " + searchTerm);
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        //Search Box
        jTextField1.selectAll();
    }//GEN-LAST:event_jTextField1FocusGained

    private void genresBoxCheck() {
        System.out.println("menu: " + jComboBox2.getItemAt(jComboBox2.getSelectedIndex()));
        UIsaver.setMenuGenre(jComboBox2.getItemAt(jComboBox2.getSelectedIndex()));

        if (jComboBox2.getItemAt(jComboBox2.getSelectedIndex()).equals("All Files")) {
            movieView = true;
            seriesView = true;
            genreSort = false;
        } else if (jComboBox2.getItemAt(jComboBox2.getSelectedIndex()).equals("Movies")) {
            movieView = true;
            seriesView = false;
            genreSort = false;
        } else if (jComboBox2.getItemAt(jComboBox2.getSelectedIndex()).equals("TV Series")) {
            movieView = false;
            seriesView = true;
            genreSort = false;
        } else {
            genreSort = true;
            genreSelect = jComboBox2.getItemAt(jComboBox2.getSelectedIndex());
            movieView = true;
            seriesView = false;
        }

    }
    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        String genrePrevious = genreSelect;
        genresBoxCheck();

//2-24 might add check so it only reloads UI if something is changed  
        System.out.println("jComboBox changed to: " + jComboBox2.getItemAt(jComboBox2.getSelectedIndex()));
        System.out.println("genrePrevious is: " + genrePrevious);
        if (!genrePrevious.equals(jComboBox2.getItemAt(jComboBox2.getSelectedIndex()))) {
            System.out.println("RUN UI from jComboBox2, genres select");
            runUICode();
            genreSelect = jComboBox2.getItemAt(jComboBox2.getSelectedIndex());
            System.out.println("genreSelect saved to variable as: " + genreSelect);
           // UIsaver.saveUIState(saveUIFileName);
            saveUI();
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jCheckBoxMenuGenresViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuGenresViewActionPerformed
        UIsaver.setGenresView(jCheckBoxMenuGenresView.getState());
        //UIsaver.saveUIState(saveUIFileName);
        saveUI();
        System.out.println("RUN UI from genres View");
        runUICode();
    }//GEN-LAST:event_jCheckBoxMenuGenresViewActionPerformed

    private void jCheckBoxMenuPlayCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuPlayCountActionPerformed
        UIsaver.setPlayCountView(jCheckBoxMenuPlayCount.getState());
       // UIsaver.saveUIState(saveUIFileName);
        saveUI();
        viewCountView = jCheckBoxMenuPlayCount.getState();
        System.out.println("RUN UI from playcount view");
        runUICode();
    }//GEN-LAST:event_jCheckBoxMenuPlayCountActionPerformed

    private void jCheckBoxMenuRatingViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuRatingViewActionPerformed
        UIsaver.setRatingsView(jCheckBoxMenuRatingView.getState());
        //UIsaver.saveUIState(saveUIFileName);
        saveUI();
        ratingView = jCheckBoxMenuRatingView.getState();
        System.out.println("RUN UI from Rating view");
        runUICode();
    }//GEN-LAST:event_jCheckBoxMenuRatingViewActionPerformed

    private void jCheckBoxMenuYearsViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuYearsViewActionPerformed
        UIsaver.setYearView(jCheckBoxMenuYearsView.getState());
        //UIsaver.saveUIState(saveUIFileName);
        saveUI();
        yearView = jCheckBoxMenuYearsView.getState();
        System.out.println("RUN UI from Year view");
        runUICode();
    }//GEN-LAST:event_jCheckBoxMenuYearsViewActionPerformed

    private void jCheckBoxMenuTitlesViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuTitlesViewActionPerformed
        UIsaver.setTitleView(jCheckBoxMenuTitlesView.getState());
        //UIsaver.saveUIState(saveUIFileName);
        saveUI();

        titleView = jCheckBoxMenuTitlesView.getState();
        System.out.println("RUN UI from Titles view");
        runUICode();
    }//GEN-LAST:event_jCheckBoxMenuTitlesViewActionPerformed

    private void videoFilePopulate() {
        if (!fileLoader.checkForVideoFileSave(fileDirectory, saveVideoFileName)) { // if the save file doesn't exist
            movieList = fileLoader.gatherFiles(fileDirectory);
            System.out.println("movieList size: " + movieList.size());

            videoFileLoader.saveVideoFiles(movieList, saveVideoFileName, fileDirectory);
        } else { //the save file does exist
            //add code here to clear temporary images
            movieList = fileLoader.updateGatherFiles(fileDirectory, movieList);
            imagesLoaded = false;
            System.out.println("Already A VideoFile, not implemented in VideoFilePopulate");
        }
    }

    public void loadVideoFiles(String fileName) {
        movieList = videoFileLoader.loadVideoFiles(fileName);
        videoFilesLoaded = true;
        System.out.println("loaded VideoFile, size: " + movieList.size());
    }

    private void jMenuUpdateDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuUpdateDataActionPerformed
        //User clicks "Get My Movies"
        if (!fileDirectory.equals("Not Set")) { //if the directory is set, search it for video files
            fileLoader.setDirectory(fileDirectory);
            videoFilePopulate();
            loadImages();
            System.out.println("calling RUN UI from jMenuUpdateDataActionPerformed");
            UIunPaused = true;
            runUICode();

        }

    }//GEN-LAST:event_jMenuUpdateDataActionPerformed

    private void jMenuMovieSetupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuMovieSetupActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenuMovieSetupActionPerformed

    private void jCheckBoxMenuItemEnableEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemEnableEditActionPerformed
        // TODO add your handling code here:
        editEnabled = jCheckBoxMenuItemEnableEdit.getState();
    }//GEN-LAST:event_jCheckBoxMenuItemEnableEditActionPerformed

    private void jMenuDownloadDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuDownloadDataActionPerformed
        //does the IMDB scraping for movieList
        fileLoader.setIMDBMiner(miner);

        for (VideoFile movie : movieList) {
            miner.gatherIMDBData(movie);
        }
        imagesLoaded = false;
        videoFileLoader.saveVideoFiles(movieList, saveVideoFileName, fileDirectory);
        System.out.println("Calling RUN UI from jMenuDownloadDataAction");
        loadImages();
        runUICode();

    }//GEN-LAST:event_jMenuDownloadDataActionPerformed

    private void sortingCheck() {
        if (movieList.size() > 0) {
            System.out.println("Calling RUN UI from sortingCheck");
            if (jComboBoxSorter.getItemAt(jComboBoxSorter.getSelectedIndex()).equals("Rating")) {
                movieList = sorter.sortRating(movieList);
            } else if (jComboBoxSorter.getItemAt(jComboBoxSorter.getSelectedIndex()).equals("Title")) {
                movieList = sorter.lexicoSort(movieList);
            } else if (jComboBoxSorter.getItemAt(jComboBoxSorter.getSelectedIndex()).equals("Year")) {
                movieList = sorter.sortYear(movieList);
            } else if (jComboBoxSorter.getItemAt(jComboBoxSorter.getSelectedIndex()).equals("Views")) {
                movieList = sorter.sortViews(movieList);
            }
        }
    }
    private void jComboBoxSorterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSorterActionPerformed
        sortingCheck();
//2-24 make it only runUI if it's changed   
        if (!sortOption.equals(jComboBoxSorter.getItemAt(jComboBoxSorter.getSelectedIndex()))) {
            runUICode();
            sortOption = jComboBoxSorter.getItemAt(jComboBoxSorter.getSelectedIndex());
        }
    }//GEN-LAST:event_jComboBoxSorterActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
 /*
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MovieInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MovieInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MovieInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MovieInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MovieInterface().setVisible(true);

            }

        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSaveSize;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuGenresView;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemEnableEdit;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuPlayCount;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuRatingView;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuTitlesView;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuYearsView;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBoxSorter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelDisplayCount;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuDirectoryPick;
    private javax.swing.JMenu jMenuDisplay;
    private javax.swing.JMenuItem jMenuDownloadData;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItemSetImageSize;
    private javax.swing.JMenu jMenuMovieSetup;
    private javax.swing.JMenuItem jMenuUpdateData;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelPrep;
    private javax.swing.JScrollPane jScrollPaneDisplay;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSlider jSliderImageSize;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
